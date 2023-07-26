import React from 'react';
import './PageContainer.css';
import Footer from '../Footer/Footer';

interface Props {
  children: React.ReactNode;
}

const PageContainer: React.FC<Props> = ({ children }) => {
  return (
    <div className="page-container">
      <div className="page-header">
        <h1>Security Command Center</h1>
      </div>
      {children}
      <Footer />
    </div>
  );
};
export default PageContainer;
