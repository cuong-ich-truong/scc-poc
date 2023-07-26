export interface CCTV {
  id: string;
  cameraName: string;
  streamUrl: string;
  floorName: string;
  incidents: Incident[];
}

export interface Incident {
  id: string;
  message: string;
  sent: boolean;
}
