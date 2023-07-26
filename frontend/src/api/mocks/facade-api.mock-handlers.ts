import { rest } from 'msw';
import { API_BASE_URL } from '../api';
import { mockedUserResponse } from './mockdata/mocked-user.response';
import { mockedCCTVsResponse } from './mockdata/mocked-cctvs.response';
import { mockedGenericResponse } from './mockdata/mocked-generic.response';

export const facadeApiMockHandlers = [
  rest.post(`${API_BASE_URL}/login`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(mockedUserResponse));
  }),

  rest.get(`${API_BASE_URL}/cctvs`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(mockedCCTVsResponse));
  }),

  rest.put(`${API_BASE_URL}/incidents/alert`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(mockedGenericResponse));
  }),

  rest.put(`${API_BASE_URL}/incidents/ignore`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json({ ...mockedGenericResponse, message: 'Ignore alert successfully' }));
  }),
];
