import { API_BASE_URL, post } from './api';
import { LoginRequest } from './dto/login.request';
import { User } from '../types/User';

export const login = async (username: string, password: string): Promise<User> => {
  const request: LoginRequest = { username, password };
  return await post<User>(`${API_BASE_URL}/login`, request);
};
