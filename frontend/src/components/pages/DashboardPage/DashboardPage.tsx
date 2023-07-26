import './DashboardPage.css';
import React, { useEffect, useState } from 'react';
import PageContainer from '../../layout/PageContainer/PageContainer';
import { login } from '../../../api/session.api';
import { useLocation } from 'react-router-dom';
import { User } from '../../../types/User';
import { Col, Input, Row } from 'reactstrap';
import { ACCESS_TOKEN_KEY, USER_ID_KEY, USERNAME_KEY, writeToSessionStorage } from '../../../utils/sessionStorage';
import { getCCTVStreams } from '../../../api/cctv-streams.api';
import { CCTV, Incident } from '../../../types/CCTV';
import { ignoreAlert, sendAlert } from '../../../api/incidents.api';
import IncidentAlert from '../../components/IncidentAlert/IncidentAlert';
import CCTVPlayer from '../../components/CCTVPlayer/CCTVPlayer';

const DashboardPage: React.FC = () => {
  let { state } = useLocation();
  const [user, setUser] = useState<User>();
  const [cctvs, setCCTVs] = useState<CCTV[]>([]);
  const [allCCTVs, setAllCCTVs] = useState<CCTV[]>([]);
  const [floors, setFloors] = useState<string[]>([]);

  useEffect(() => {
    login('username', 'password').then((user_) => {
      writeToSessionStorage(USER_ID_KEY, user_.id);
      writeToSessionStorage(USERNAME_KEY, user_.username);
      writeToSessionStorage(ACCESS_TOKEN_KEY, user_.accessToken);
      state = { user: user_ };
      setUser(user_);

      getCCTVStreams().then((cctvs_) => {
        setAllCCTVs(cctvs_);
        updateFloorsFromCCTVs(cctvs_);
      });
    });
  }, []);

  const updateFloorsFromCCTVs = (cctvs: CCTV[]) => {
    setFloors(
      cctvs.reduce((floors_: string[], cctv: CCTV) => {
        if (!floors_.includes(cctv.floorName)) {
          floors_.push(cctv.floorName);
        }
        return floors_;
      }, [])
    );
  };

  const updateCCTVs = (floorName: string) => setCCTVs(allCCTVs.filter((cctv) => cctv.floorName === floorName));

  const onSendAlert = async (incident?: Incident) => {
    if (incident) {
      await sendAlert(incident.id);
      incident.sent = true;
      setCCTVs([...cctvs]);
    }
  };

  const onIgnoreAlert = async (incident?: Incident) => {
    if (incident) {
      await ignoreAlert(incident.id);
      incident.sent = true;
      setCCTVs([...cctvs]);
    }
  };

  return (
    <PageContainer user={user}>
      <div className="dashboard-page-container">
        <div className="cctv-property-input">
          <Input type="select" id="floors" name="floors" onChange={(e) => updateCCTVs(e.target.value)}>
            <option defaultChecked={true}>Select Floor</option>
            {floors.map((floor: string) => (
              <option key={floor} value={floor}>
                Floor {floor}
              </option>
            ))}
          </Input>
        </div>
        <Row xs="2" className="m-0 cctvs-container">
          {cctvs.map((cctv) => (
            <Col key={cctv.id} className="bg-light border">
              <div className="camera-info-container">
                <span>{cctv.cameraName}</span>
                <IncidentAlert cctv={cctv} onSendAlert={onSendAlert} onIgnoreAlert={onIgnoreAlert} />
              </div>
              <CCTVPlayer cctv={cctv} />
            </Col>
          ))}
        </Row>
      </div>
    </PageContainer>
  );
};
export default DashboardPage;
