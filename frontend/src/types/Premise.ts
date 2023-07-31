export interface Premise {
  id: string;
  name: string;
  cameras: Camera[];
}

export interface Camera {
  id: string;
  name: string;
  streamUrl: string;
  incidents?: Incident[];
}

export interface Incident {
  id: string;
  message: string;
  ignore: boolean;
  guardId?: string;
  dateCreated: string;
}
