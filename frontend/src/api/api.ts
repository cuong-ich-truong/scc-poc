import { ExceptionResponse } from './dto/ExceptionResponse';
import { ApiException } from './api.exception';

const serverBaseUrl = import.meta.env.VITE_SERVER_BASE_URL ?? '';
const basePathPrefix = import.meta.env.VITE_BASE_PATH_PREFIX ?? '';

export const API_BASE_URL = `${serverBaseUrl}${basePathPrefix}/api`;
export const CONTENT_TYPE_HEADER_KEY = 'Content-Type';

export type RequestMethod = 'GET' | 'POST' | 'PUT';

const sendRequest = async <T>(
  url: string,
  method: RequestMethod,
  data?: any,
  headers: Record<string, string> = { [CONTENT_TYPE_HEADER_KEY]: 'application/json' }
): Promise<T> => {
  const response = await fetch(url, {
    method,
    headers: {
      ...headers,
    },
    body: headers[CONTENT_TYPE_HEADER_KEY] === 'application/json' ? JSON.stringify(data) : data,
  });

  const responseBody = await response.json();
  if (!response.ok) {
    const error = responseBody as ExceptionResponse;
    console.error('The server responded with error: ', error.message);
    throw new ApiException(error.status, error.message);
  }
  return responseBody;
};

export const get = <T>(url: string): Promise<T> => sendRequest<T>(url, 'GET', undefined);

export const post = <T>(
  url: string,
  data: any,
  headers: Record<string, string> = { [CONTENT_TYPE_HEADER_KEY]: 'application/json' }
): Promise<T> => sendRequest<T>(url, 'POST', data, headers);

export const put = <T>(url: string, data: any): Promise<T> => sendRequest<T>(url, 'PUT', data);
