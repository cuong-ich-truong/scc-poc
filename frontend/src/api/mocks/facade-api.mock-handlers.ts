import { mockedUserResponse } from './mockdata/mocked-user.response';
import { rest } from 'msw';
import { API_BASE_URL } from '../api';

export const facadeApiMockHandlers = [
  rest.post(`${API_BASE_URL}/login`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(mockedUserResponse));
  }),
];
