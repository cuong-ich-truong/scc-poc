import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import { Toaster } from 'react-hot-toast';
import { getApplicationVersion } from './utils/getApplicationVersion';

console.log(`Application version: ${getApplicationVersion()}`);

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
      <Toaster position="bottom-center" containerStyle={{ fontSize: '11pt' }} />
    </BrowserRouter>
  </React.StrictMode>
);
(document.getElementById('root') as HTMLElement).className = 'container';
