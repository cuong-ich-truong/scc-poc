import { API_BASE_URL, put } from './api';
import { GenericResponse } from './dto/generic.response';

export const sendAlert = async (incidentId, guardId): Promise<GenericResponse> => {
  return await put<GenericResponse>(`${API_BASE_URL}/incidents/${incidentId}`, { guardId: guardId });
};

export const ignoreAlert = async (incidentId): Promise<GenericResponse> => {
  return await put<GenericResponse>(`${API_BASE_URL}/incidents/${incidentId}`, { ignore: true });
};
