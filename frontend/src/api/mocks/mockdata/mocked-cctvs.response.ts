import { CCTV } from '../../../types/CCTV';

export const mockedCCTVsResponse: CCTV[] = [
  {
    id: '63c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '1',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Stranger detected',
        sent: false,
      },
    ],
  },
  {
    id: '73c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '1',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Dog detected',
        sent: true,
      },
    ],
  },
  {
    id: '83c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '1',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Cat detected',
        sent: true,
      },
    ],
  },
  {
    id: '93c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '1',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Mouse detected',
        sent: true,
      },
    ],
  },
  {
    id: '63c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '2',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Stranger detected',
        sent: false,
      },
    ],
  },
  {
    id: '73c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '2',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Dog detected',
        sent: true,
      },
    ],
  },
  {
    id: '83c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '2',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Cat detected',
        sent: true,
      },
    ],
  },
  {
    id: '93c0cf4cbb90b139dd6f6804',
    streamUrl: 'url',
    floorName: '2',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Mouse detected',
        sent: true,
      },
    ],
  },
];
