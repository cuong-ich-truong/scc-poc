import React, { useEffect, useState } from 'react';
import 'regenerator-runtime/runtime';
import {
  Button,
  ListGroup,
  ListGroupItem,
  Modal,
  ModalBody,
  ModalHeader,
  PopoverBody,
  UncontrolledPopover,
} from 'reactstrap';
import { CgDanger } from 'react-icons/cg';
import { BsThreeDots } from 'react-icons/bs';
import { Camera, Incident } from '../../../types/Premise';
import { hasActiveIncident, isIncidentActive } from '../../pages/DashboardPage/findActiveIncidents';
import { testId } from '../../../testing/testId';
import { User } from '../../../types/User';
import moment from 'moment';
import './IncidentsListPopup.css';

export interface Props {
  cctv: Camera;
  guards: User[];
  onSendAlert: (incident: Incident, guardId: string) => Promise<void>;
  onIgnoreAlert: (incident: Incident) => Promise<void>;
}
const IncidentsListPopup: React.FC<Props> = ({ cctv, guards, onSendAlert, onIgnoreAlert }) => {
  const [incidents, setIncidents] = useState<Incident[]>([]);
  const [modal, setModal] = useState(false);
  const toggle = () => setModal(!modal);

  useEffect(() => {
    if (cctv.incidents) {
      setIncidents(cctv.incidents);
    }
  }, []);

  const onSendAlert_ = async (incident: Incident, guardId: string) => {
    await onSendAlert(incident, guardId);
    setIncidents([...incidents]);
  };

  const onIgnoreAlert_ = async (incident: Incident) => {
    await onIgnoreAlert(incident);
    setIncidents([...incidents]);
  };

  const getGuardName = (guardId: string) => guards.find((guard) => guard.id === guardId)?.firstname ?? '';

  return (
    <div className="camera-incident-container">
      <Button
        type="button"
        color={hasActiveIncident(incidents) ? 'outline-danger' : ''}
        onClick={toggle}
        data-testid={testId.showIncidentPopoverButton}
      >
        {hasActiveIncident(incidents) && (
          <>
            <CgDanger />{' '}
          </>
        )}
        <BsThreeDots size={20} />
      </Button>
      <Modal isOpen={modal} toggle={toggle} data-testid={testId.alertPopup}>
        <ModalHeader toggle={toggle}>{cctv.name} - Alerts List</ModalHeader>
        <ModalBody>
          <ListGroup flush>
            {incidents.length < 1 && <p className="m-3">There are no alerts from this camera</p>}
            {incidents
              .sort((left, right) => (left.dateCreated < right.dateCreated ? 1 : -1))
              .map((incident) => (
                <ListGroupItem key={incident.id} className="incident-container" data-testid={testId.alertListGroupItem}>
                  <span>
                    {moment(incident.dateCreated).format('DD/MM/YYYY hh:mm A') +
                      `${incident.ignore ? ' - Ignored' : ''}` +
                      `${incident.guardId !== undefined ? ' - Assigned to: ' + getGuardName(incident.guardId) : ''}`}
                  </span>
                  {isIncidentActive(incident) && (
                    <div className="incident-buttons-container">
                      <Button
                        id={`popover-${incident.id}`}
                        type="button"
                        color="danger"
                        data-testid={testId.sendAlertButton}
                      >
                        Send Alert
                      </Button>
                      <UncontrolledPopover
                        target={`popover-${incident.id}`}
                        data-testid={testId.incidentPopoverContainer}
                      >
                        <PopoverBody>
                          <ListGroup flush className="guards-buttons-container">
                            {guards.map((guard) => (
                              <ListGroupItem key={guard.id}>
                                <Button
                                  type="button"
                                  onClick={() => onSendAlert_(incident, guard.id)}
                                  data-testid={testId.alertGuardButton}
                                >
                                  {guard.firstname}
                                </Button>
                              </ListGroupItem>
                            ))}
                          </ListGroup>
                        </PopoverBody>
                      </UncontrolledPopover>
                      <Button
                        type="button"
                        color="outline-dark"
                        data-testid={testId.ignoreAlertButton}
                        onClick={() => onIgnoreAlert_(incident)}
                      >
                        Ignore
                      </Button>
                    </div>
                  )}
                </ListGroupItem>
              ))}
          </ListGroup>
        </ModalBody>
      </Modal>
    </div>
  );
};
export default IncidentsListPopup;
