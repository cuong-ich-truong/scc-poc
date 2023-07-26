import './DashboardPage.css';
import React, { useEffect, useState } from 'react';
import PageContainer from '../../layout/PageContainer/PageContainer';
import { login } from '../../../api/session.api';
import { useLocation } from 'react-router-dom';
import { User } from '../../../types/User';
import { Input } from 'reactstrap';
import { ACCESS_TOKEN_KEY, USER_ID_KEY, USERNAME_KEY, writeToSessionStorage } from '../../../utils/sessionStorage';
import { getCCTVStreams } from '../../../api/cctv-streams.api';
import { CCTV } from '../../../types/CCTV';

const DashboardPage: React.FC = () => {
  let { state } = useLocation();
  const [user, setUser] = useState<User>();
  const [cctvs, setCCTVs] = useState<CCTV[]>();
  const [allCCTVs, setAllCCTVs] = useState<CCTV[]>();
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

  const onFloorChange = (e) => {};

  return (
    <PageContainer user={user}>
      <div className="login-page-container">
        <div className="cctv-property-input">
          <Input type="select" id="floors" name="floors" onChange={onFloorChange}>
            {floors.map((floor: string) => (
              <option key={floor} value={floor}>
                Floor {floor}
              </option>
            ))}
          </Input>
        </div>
      </div>
    </PageContainer>
  );
};
export default DashboardPage;
