import './DashboardPage.css';
import React, { useEffect, useState } from 'react';
import PageContainer from '../../layout/PageContainer/PageContainer';
import { getGuards, login } from '../../../api/users.api';
import { useLocation } from 'react-router-dom';
import { User } from '../../../types/User';
import { Col, Input, Row } from 'reactstrap';
import { ACCESS_TOKEN_KEY, USER_ID_KEY, USERNAME_KEY, writeToSessionStorage } from '../../../utils/sessionStorage';
import { getPremises } from '../../../api/premises.api';
import { Incident, Premise } from '../../../types/Premise';
import { ignoreAlert, sendAlert } from '../../../api/incidents.api';
import IncidentsListPopup from '../../components/IncidentsListPopup/IncidentsListPopup';
import CCTVPlayer from '../../components/CCTVPlayer/CCTVPlayer';
import { testId } from '../../../testing/testId';

const DashboardPage: React.FC = () => {
  let { state } = useLocation();
  const [user, setUser] = useState<User>();
  const [premises, setPremises] = useState<Premise[]>([]);
  const [selectedPremise, setSelectedPremise] = useState<Premise>();
  const [guards, setGuards] = useState<User[]>([]);

  useEffect(() => {
    login('username', 'password').then((user_) => {
      writeToSessionStorage(USER_ID_KEY, user_.id);
      writeToSessionStorage(USERNAME_KEY, user_.username);
      writeToSessionStorage(ACCESS_TOKEN_KEY, user_.accessToken);
      state = { user: user_ };
      setUser(user_);

      getGuards().then((guards_) => setGuards(guards_));
      getPremises().then((premises_) => setPremises(premises_));
    });
  }, []);

  const onSendAlert = async (incident: Incident, guardId: string) => {
    await sendAlert(incident.id, guardId);
    incident.guardId = guardId;
  };

  const onIgnoreAlert = async (incident: Incident) => {
    await ignoreAlert(incident.id);
    incident.ignore = true;
  };

  const onChangePremise = (e) => {
    const premise = premises.find((premise_) => premise_.id === e.target.value);
    if (premise) {
      setSelectedPremise(premise);
    } else {
      setSelectedPremise(undefined);
    }
  };

  return (
    <PageContainer user={user}>
      <div className="dashboard-page-container">
        <div className="camera-property-input">
          <Input
            type="select"
            id="premises"
            name="premises"
            data-testid={testId.premisesDropdown}
            onChange={onChangePremise}
          >
            <option defaultChecked={true}>Select Floor</option>
            {premises.map((premise: Premise) => (
              <option key={premise.id} value={premise.id}>
                {premise.name}
              </option>
            ))}
          </Input>
        </div>
        <Row xs="2" className="m-0 cctvs-container">
          {selectedPremise &&
            selectedPremise.cameras.map((camera) => (
              <Col key={camera.id} className="bg-light border">
                <div className="camera-info-container">
                  <span>{camera.name}</span>
                  <IncidentsListPopup
                    cctv={camera}
                    guards={guards}
                    onSendAlert={onSendAlert}
                    onIgnoreAlert={onIgnoreAlert}
                  />
                </div>
                <CCTVPlayer cctv={camera} />
              </Col>
            ))}
        </Row>
      </div>
    </PageContainer>
  );
};
export default DashboardPage;
