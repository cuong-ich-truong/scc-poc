import { Incident } from '../../../types/CCTV';

export const findNonSentIncident = (incidents: Incident[]) => incidents.find((incident) => !incident.sent);
