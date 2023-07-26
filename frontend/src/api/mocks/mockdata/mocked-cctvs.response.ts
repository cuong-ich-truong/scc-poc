import { CCTV } from '../../../types/CCTV';

export const mockedCCTVsResponse: CCTV[] = [
  {
    id: '63c0cf4cbb90b139dd6f6804',
    streamUrl: 'https://s2.livecam-pro.live/cam056/tracks-v1/mono.m3u8',
    cameraName: 'Camera 1',
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
    streamUrl: 'https://s2.livecam-pro.live/cam062/tracks-v1/mono.m3u8',
    cameraName: 'Camera 2',
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
    streamUrl: 'https://s2.livecam-pro.live/cam070/tracks-v1/mono.m3u8',
    cameraName: 'Camera 3',
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
    streamUrl: 'https://s2.livecam-pro.live/cam007/tracks-v1/mono.m3u8',
    cameraName: 'Camera 4',
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
    id: '63c0cf4cbb90b139dd6f6805',
    streamUrl: 'https://s2.livecam-pro.live/cam008/tracks-v1/mono.m3u8',
    cameraName: 'Camera 5',
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
    id: '73c0cf4cbb90b139dd6f6805',
    streamUrl: 'https://s2.livecam-pro.live/cam040/tracks-v1/mono.m3u8',
    cameraName: 'Camera 6',
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
    id: '83c0cf4cbb90b139dd6f6805',
    streamUrl: 'https://s2.livecam-pro.live/cam036/tracks-v1/mono.m3u8',
    cameraName: 'Camera 7',
    floorName: '2',
    incidents: [
      {
        id: '63c0cf4cbb90b139dd6f6805',
        message: 'Cat detected',
        sent: false,
      },
    ],
  },
  {
    id: '93c0cf4cbb90b139dd6f6805',
    streamUrl: 'https://s2.livecam-pro.live/cam023/tracks-v1/mono.m3u8',
    cameraName: 'Camera 8',
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
