import { API_BASE_URL, put } from './api';
import { GenericResponse } from './dto/generic.response';

export const sendAlert = async (incidentId): Promise<GenericResponse> => {
  return await put<GenericResponse>(`${API_BASE_URL}/incidents/alert`, { incidentId: incidentId });
};

export const ignoreAlert = async (incidentId): Promise<GenericResponse> => {
  return await put<GenericResponse>(`${API_BASE_URL}/incidents/ignore`, { incidentId: incidentId });
};
