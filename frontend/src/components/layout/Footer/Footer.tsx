import React from 'react';
import { getApplicationVersion } from '../../../utils/getApplicationVersion';

const Footer: React.FC = () => {
  return (
    <div
      style={{ height: '35px', paddingLeft: '25px', fontSize: '10pt', color: 'gray' }}
    >{`Application version: ${getApplicationVersion()}`}</div>
  );
};
export default Footer;
