import { API_BASE_URL, get } from './api';
import { CCTV } from '../types/CCTV';

export const getCCTVStreams = async (): Promise<CCTV[]> => {
  return await get<CCTV[]>(`${API_BASE_URL}/cctvs`);
};
