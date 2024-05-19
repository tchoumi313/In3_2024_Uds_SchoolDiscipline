/* tslint:disable */
/* eslint-disable */
/**
 * Mon API
 * Documentation de l'API de votre application.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import globalAxios, { AxiosPromise, AxiosInstance } from 'axios';
import { Configuration } from '../configuration';
// Some imports not used depending on template conditions
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from '../base';
import { InlineResponse2003 } from '../models';
import { InlineResponse2004 } from '../models';
import { InlineResponse2005 } from '../models';
import { InlineResponse2006 } from '../models';
import { InlineResponse2011 } from '../models';
import { InlineResponse4001 } from '../models';
import { InlineResponse4002 } from '../models';
import { InlineResponse401 } from '../models';
import { InlineResponse404 } from '../models';
import { InlineResponse4041 } from '../models';
import { InlineResponse4042 } from '../models';
import { InlineResponse4221 } from '../models';
/**
 * AvoirmembreconseildisciplinesApi - axios parameter creator
 * @export
 */
export const AvoirmembreconseildisciplinesApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * Retrieve a list of all avoirmembreconseildisciplines
         * @summary Get all avoirmembreconseildisciplines
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        avoirmembreconseildisciplinesIndex: async (authorization: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'authorization' is not null or undefined
            if (authorization === null || authorization === undefined) {
                throw new RequiredError('authorization','Required parameter authorization was null or undefined when calling avoirmembreconseildisciplinesIndex.');
            }
            const localVarPath = `/api/avoirmembreconseildisciplines/findAll`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (authorization !== undefined && authorization !== null) {
                localVarHeaderParameter['Authorization'] = String(authorization);
            }

            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.query) {
                query.set(key, options.query[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
        /**
         * Create a new avoirmembreconseildiscipline resource
         * @summary Create a new avoirmembreconseildiscipline
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createAvoirMembreConseilDiscipline: async (idMembreConseil: number, idConseilDiscipline: number, authorization: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'idMembreConseil' is not null or undefined
            if (idMembreConseil === null || idMembreConseil === undefined) {
                throw new RequiredError('idMembreConseil','Required parameter idMembreConseil was null or undefined when calling createAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'idConseilDiscipline' is not null or undefined
            if (idConseilDiscipline === null || idConseilDiscipline === undefined) {
                throw new RequiredError('idConseilDiscipline','Required parameter idConseilDiscipline was null or undefined when calling createAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'authorization' is not null or undefined
            if (authorization === null || authorization === undefined) {
                throw new RequiredError('authorization','Required parameter authorization was null or undefined when calling createAvoirMembreConseilDiscipline.');
            }
            const localVarPath = `/api/avoirmembreconseildisciplines/create`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;
            const localVarFormParams = new FormData();

            if (authorization !== undefined && authorization !== null) {
                localVarHeaderParameter['Authorization'] = String(authorization);
            }


            if (idMembreConseil !== undefined) { 
                localVarFormParams.append('idMembreConseil', idMembreConseil as any);
            }

            if (idConseilDiscipline !== undefined) { 
                localVarFormParams.append('idConseilDiscipline', idConseilDiscipline as any);
            }

            localVarHeaderParameter['Content-Type'] = 'multipart/form-data';
            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.query) {
                query.set(key, options.query[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = localVarFormParams;

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
        /**
         * Delete an avoirmembreconseildiscipline resource
         * @summary Delete an avoirmembreconseildiscipline
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to delete
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteAvoirMembreConseilDiscipline: async (authorization: string, id: number, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'authorization' is not null or undefined
            if (authorization === null || authorization === undefined) {
                throw new RequiredError('authorization','Required parameter authorization was null or undefined when calling deleteAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'id' is not null or undefined
            if (id === null || id === undefined) {
                throw new RequiredError('id','Required parameter id was null or undefined when calling deleteAvoirMembreConseilDiscipline.');
            }
            const localVarPath = `/api/avoirmembreconseildisciplines/delete/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'DELETE', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (authorization !== undefined && authorization !== null) {
                localVarHeaderParameter['Authorization'] = String(authorization);
            }

            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.query) {
                query.set(key, options.query[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
        /**
         * Update a avoirmembreconseildiscipline's information
         * @summary Update a avoirmembreconseildiscipline's information
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {number} avoirmembreconseildisciplineId ID of avoirmembreconseildiscipline to update in this request
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateAvoirMembreConseilDiscipline: async (idMembreConseil: number, idConseilDiscipline: number, authorization: string, avoirmembreconseildisciplineId: number, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'idMembreConseil' is not null or undefined
            if (idMembreConseil === null || idMembreConseil === undefined) {
                throw new RequiredError('idMembreConseil','Required parameter idMembreConseil was null or undefined when calling updateAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'idConseilDiscipline' is not null or undefined
            if (idConseilDiscipline === null || idConseilDiscipline === undefined) {
                throw new RequiredError('idConseilDiscipline','Required parameter idConseilDiscipline was null or undefined when calling updateAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'authorization' is not null or undefined
            if (authorization === null || authorization === undefined) {
                throw new RequiredError('authorization','Required parameter authorization was null or undefined when calling updateAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'avoirmembreconseildisciplineId' is not null or undefined
            if (avoirmembreconseildisciplineId === null || avoirmembreconseildisciplineId === undefined) {
                throw new RequiredError('avoirmembreconseildisciplineId','Required parameter avoirmembreconseildisciplineId was null or undefined when calling updateAvoirMembreConseilDiscipline.');
            }
            const localVarPath = `/api/avoirmembreconseildisciplines/update/{avoirmembreconseildisciplineId}`
                .replace(`{${"avoirmembreconseildisciplineId"}}`, encodeURIComponent(String(avoirmembreconseildisciplineId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;
            const localVarFormParams = new FormData();

            if (authorization !== undefined && authorization !== null) {
                localVarHeaderParameter['Authorization'] = String(authorization);
            }


            if (idMembreConseil !== undefined) { 
                localVarFormParams.append('idMembreConseil', idMembreConseil as any);
            }

            if (idConseilDiscipline !== undefined) { 
                localVarFormParams.append('idConseilDiscipline', idConseilDiscipline as any);
            }

            localVarHeaderParameter['Content-Type'] = 'multipart/form-data';
            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.query) {
                query.set(key, options.query[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = localVarFormParams;

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
        /**
         * Get information about a specific avoirmembreconseildiscipline
         * @summary Get avoirmembreconseildiscipline information
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to get information for
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        viewAvoirMembreConseilDiscipline: async (authorization: string, id: number, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'authorization' is not null or undefined
            if (authorization === null || authorization === undefined) {
                throw new RequiredError('authorization','Required parameter authorization was null or undefined when calling viewAvoirMembreConseilDiscipline.');
            }
            // verify required parameter 'id' is not null or undefined
            if (id === null || id === undefined) {
                throw new RequiredError('id','Required parameter id was null or undefined when calling viewAvoirMembreConseilDiscipline.');
            }
            const localVarPath = `/api/avoirmembreconseildisciplines/findOne/{id}`
                .replace(`{${"id"}}`, encodeURIComponent(String(id)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            if (authorization !== undefined && authorization !== null) {
                localVarHeaderParameter['Authorization'] = String(authorization);
            }

            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.query) {
                query.set(key, options.query[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * AvoirmembreconseildisciplinesApi - functional programming interface
 * @export
 */
export const AvoirmembreconseildisciplinesApiFp = function(configuration?: Configuration) {
    return {
        /**
         * Retrieve a list of all avoirmembreconseildisciplines
         * @summary Get all avoirmembreconseildisciplines
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async avoirmembreconseildisciplinesIndex(authorization: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse2003>> {
            const localVarAxiosArgs = await AvoirmembreconseildisciplinesApiAxiosParamCreator(configuration).avoirmembreconseildisciplinesIndex(authorization, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
        /**
         * Create a new avoirmembreconseildiscipline resource
         * @summary Create a new avoirmembreconseildiscipline
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse2011>> {
            const localVarAxiosArgs = await AvoirmembreconseildisciplinesApiAxiosParamCreator(configuration).createAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
        /**
         * Delete an avoirmembreconseildiscipline resource
         * @summary Delete an avoirmembreconseildiscipline
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to delete
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async deleteAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse2006>> {
            const localVarAxiosArgs = await AvoirmembreconseildisciplinesApiAxiosParamCreator(configuration).deleteAvoirMembreConseilDiscipline(authorization, id, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
        /**
         * Update a avoirmembreconseildiscipline's information
         * @summary Update a avoirmembreconseildiscipline's information
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {number} avoirmembreconseildisciplineId ID of avoirmembreconseildiscipline to update in this request
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async updateAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, avoirmembreconseildisciplineId: number, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse2005>> {
            const localVarAxiosArgs = await AvoirmembreconseildisciplinesApiAxiosParamCreator(configuration).updateAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, avoirmembreconseildisciplineId, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
        /**
         * Get information about a specific avoirmembreconseildiscipline
         * @summary Get avoirmembreconseildiscipline information
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to get information for
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async viewAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse2004>> {
            const localVarAxiosArgs = await AvoirmembreconseildisciplinesApiAxiosParamCreator(configuration).viewAvoirMembreConseilDiscipline(authorization, id, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * AvoirmembreconseildisciplinesApi - factory interface
 * @export
 */
export const AvoirmembreconseildisciplinesApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * Retrieve a list of all avoirmembreconseildisciplines
         * @summary Get all avoirmembreconseildisciplines
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        avoirmembreconseildisciplinesIndex(authorization: string, options?: any): AxiosPromise<InlineResponse2003> {
            return AvoirmembreconseildisciplinesApiFp(configuration).avoirmembreconseildisciplinesIndex(authorization, options).then((request) => request(axios, basePath));
        },
        /**
         * Create a new avoirmembreconseildiscipline resource
         * @summary Create a new avoirmembreconseildiscipline
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, options?: any): AxiosPromise<InlineResponse2011> {
            return AvoirmembreconseildisciplinesApiFp(configuration).createAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, options).then((request) => request(axios, basePath));
        },
        /**
         * Delete an avoirmembreconseildiscipline resource
         * @summary Delete an avoirmembreconseildiscipline
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to delete
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        deleteAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any): AxiosPromise<InlineResponse2006> {
            return AvoirmembreconseildisciplinesApiFp(configuration).deleteAvoirMembreConseilDiscipline(authorization, id, options).then((request) => request(axios, basePath));
        },
        /**
         * Update a avoirmembreconseildiscipline's information
         * @summary Update a avoirmembreconseildiscipline's information
         * @param {number} idMembreConseil 
         * @param {number} idConseilDiscipline 
         * @param {string} authorization JWT token
         * @param {number} avoirmembreconseildisciplineId ID of avoirmembreconseildiscipline to update in this request
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        updateAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, avoirmembreconseildisciplineId: number, options?: any): AxiosPromise<InlineResponse2005> {
            return AvoirmembreconseildisciplinesApiFp(configuration).updateAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, avoirmembreconseildisciplineId, options).then((request) => request(axios, basePath));
        },
        /**
         * Get information about a specific avoirmembreconseildiscipline
         * @summary Get avoirmembreconseildiscipline information
         * @param {string} authorization JWT token
         * @param {number} id ID of avoirmembreconseildiscipline to get information for
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        viewAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any): AxiosPromise<InlineResponse2004> {
            return AvoirmembreconseildisciplinesApiFp(configuration).viewAvoirMembreConseilDiscipline(authorization, id, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * AvoirmembreconseildisciplinesApi - object-oriented interface
 * @export
 * @class AvoirmembreconseildisciplinesApi
 * @extends {BaseAPI}
 */
export class AvoirmembreconseildisciplinesApi extends BaseAPI {
    /**
     * Retrieve a list of all avoirmembreconseildisciplines
     * @summary Get all avoirmembreconseildisciplines
     * @param {string} authorization JWT token
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AvoirmembreconseildisciplinesApi
     */
    public avoirmembreconseildisciplinesIndex(authorization: string, options?: any) {
        return AvoirmembreconseildisciplinesApiFp(this.configuration).avoirmembreconseildisciplinesIndex(authorization, options).then((request) => request(this.axios, this.basePath));
    }
    /**
     * Create a new avoirmembreconseildiscipline resource
     * @summary Create a new avoirmembreconseildiscipline
     * @param {number} idMembreConseil 
     * @param {number} idConseilDiscipline 
     * @param {string} authorization JWT token
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AvoirmembreconseildisciplinesApi
     */
    public createAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, options?: any) {
        return AvoirmembreconseildisciplinesApiFp(this.configuration).createAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, options).then((request) => request(this.axios, this.basePath));
    }
    /**
     * Delete an avoirmembreconseildiscipline resource
     * @summary Delete an avoirmembreconseildiscipline
     * @param {string} authorization JWT token
     * @param {number} id ID of avoirmembreconseildiscipline to delete
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AvoirmembreconseildisciplinesApi
     */
    public deleteAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any) {
        return AvoirmembreconseildisciplinesApiFp(this.configuration).deleteAvoirMembreConseilDiscipline(authorization, id, options).then((request) => request(this.axios, this.basePath));
    }
    /**
     * Update a avoirmembreconseildiscipline's information
     * @summary Update a avoirmembreconseildiscipline's information
     * @param {number} idMembreConseil 
     * @param {number} idConseilDiscipline 
     * @param {string} authorization JWT token
     * @param {number} avoirmembreconseildisciplineId ID of avoirmembreconseildiscipline to update in this request
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AvoirmembreconseildisciplinesApi
     */
    public updateAvoirMembreConseilDiscipline(idMembreConseil: number, idConseilDiscipline: number, authorization: string, avoirmembreconseildisciplineId: number, options?: any) {
        return AvoirmembreconseildisciplinesApiFp(this.configuration).updateAvoirMembreConseilDiscipline(idMembreConseil, idConseilDiscipline, authorization, avoirmembreconseildisciplineId, options).then((request) => request(this.axios, this.basePath));
    }
    /**
     * Get information about a specific avoirmembreconseildiscipline
     * @summary Get avoirmembreconseildiscipline information
     * @param {string} authorization JWT token
     * @param {number} id ID of avoirmembreconseildiscipline to get information for
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AvoirmembreconseildisciplinesApi
     */
    public viewAvoirMembreConseilDiscipline(authorization: string, id: number, options?: any) {
        return AvoirmembreconseildisciplinesApiFp(this.configuration).viewAvoirMembreConseilDiscipline(authorization, id, options).then((request) => request(this.axios, this.basePath));
    }
}
