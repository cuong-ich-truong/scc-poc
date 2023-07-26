import './DashboardPage.css';
import React, { useEffect } from 'react';
import PageContainer from '../../layout/PageContainer/PageContainer';
import { login } from '../../../api/session.api';

const DashboardPage: React.FC = () => {
  useEffect(() => {
    login('username', 'password').then((user) => {
      console.log(user);
    });
  });

  return (
    <PageContainer>
      <div className="login-page-container">
        <p>This is page content</p>
      </div>
    </PageContainer>
  );
};
export default DashboardPage;
