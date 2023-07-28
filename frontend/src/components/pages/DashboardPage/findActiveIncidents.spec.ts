import { Incident } from '../../../types/Premise';
import { hasActiveIncident, isIncidentActive } from './findActiveIncidents';

describe('Find active incidents', () => {
  const activeIncident1: Incident = {
    id: '63c0cf4cbb90b139dd6f6805',
    message: 'Stranger detected',
    ignore: false,
    dateCreated: '2023-07-26T11:13:42.908Z',
  };
  const activeIncident2: Incident = {
    id: '63c0cf4cbb90b139dd6f6905',
    message: 'Stranger 2 detected',
    ignore: false,
    dateCreated: '2023-07-27T11:13:42.908Z',
  };
  const ignoredIncident: Incident = {
    id: '63c0cf4cbb90b139dd6f7805',
    message: 'Dog detected',
    ignore: true,
    dateCreated: '2023-05-15T11:12:42.908Z',
  };
  const assignedIncident: Incident = {
    id: '63c0cf4cbb90b139dd6f8805',
    message: 'Cat detected',
    ignore: false,
    guardId: '63c0cf4cbb90b139dd6f6804',
    dateCreated: '2023-05-15T11:13:42.908Z',
  };

  describe('hasActiveIncident', () => {
    it('should return true if there is only one active incident', () => {
      expect(hasActiveIncident([activeIncident1])).toEqual(true);
    });

    it('should return true if there is multiple active incidents', () => {
      expect(hasActiveIncident([activeIncident1, activeIncident2])).toEqual(true);
    });

    it('should return true if there is multiple active/inactive incidents', () => {
      expect(hasActiveIncident([activeIncident1, activeIncident2, ignoredIncident, assignedIncident])).toEqual(true);
    });

    it('should return true if there is no incidents', () => {
      expect(hasActiveIncident([])).toEqual(false);
    });

    it('should return true if there are only inactive incidents', () => {
      expect(hasActiveIncident([ignoredIncident, assignedIncident])).toEqual(false);
    });
  });

  describe('isIncidentActive', () => {
    it('should return true if incident is active', () => {
      expect(isIncidentActive(activeIncident1)).toEqual(true);
    });

    it('should return false if incident is ignored', () => {
      expect(isIncidentActive(ignoredIncident)).toEqual(false);
    });

    it('should return false if incident is assigned', () => {
      expect(isIncidentActive(assignedIncident)).toEqual(false);
    });
  });
});
