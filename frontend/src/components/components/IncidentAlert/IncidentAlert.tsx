import React from 'react';
import 'regenerator-runtime/runtime';
import { Button, PopoverBody, UncontrolledPopover } from 'reactstrap';
import { CgDanger } from 'react-icons/cg';
import { BsThreeDots } from 'react-icons/bs';
import { CCTV, Incident } from '../../../types/CCTV';
import { findNonSentIncident } from '../../pages/DashboardPage/findNonSentIncident';

export interface Props {
  cctv: CCTV;
  onSendAlert: (incident?: Incident) => void;
  onIgnoreAlert: (incident?: Incident) => void;
}
const IncidentAlert: React.FC<Props> = ({ cctv, onSendAlert, onIgnoreAlert }) => {
  const nonSentIncident = findNonSentIncident(cctv.incidents);
  return (
    <>
      {nonSentIncident !== undefined && (
        <div className="camera-incident-container">
          <Button id={`popover-${cctv.id}`} type="button" color="outline-danger">
            <CgDanger /> <BsThreeDots size={20} />
          </Button>
          <UncontrolledPopover target={`popover-${cctv.id}`} trigger="focus">
            <PopoverBody>
              <div className="incident-popover-buttons-container">
                <Button type="button" color="danger" onClick={() => onSendAlert(findNonSentIncident(cctv.incidents))}>
                  Send Alert
                </Button>
                <Button
                  type="button"
                  color="outline-dark"
                  onClick={() => onIgnoreAlert(findNonSentIncident(cctv.incidents))}
                >
                  Ignore
                </Button>
              </div>
            </PopoverBody>
          </UncontrolledPopover>
        </div>
      )}
    </>
  );
};
export default IncidentAlert;
