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
/**
 * 
 * @export
 * @interface Cour
 */
export interface Cour {
    /**
     * 
     * @type {number}
     * @memberof Cour
     */
    id: any;
    /**
     * Physique
     * @type {string}
     * @memberof Cour
     */
    libelle: any;
    /**
     * 
     * @type {string}
     * @memberof Cour
     */
    dateCour: any;
    /**
     * 
     * @type {string}
     * @memberof Cour
     */
    heureDebut: any;
    /**
     * 
     * @type {string}
     * @memberof Cour
     */
    heureFin: any;
    /**
     * 
     * @type {Professeur}
     * @memberof Cour
     */
    professeur?: any;
    /**
     * 
     * @type {Classe}
     * @memberof Cour
     */
    classes?: any;
    /**
     * 
     * @type {BaseModelpropertiescreatedAt}
     * @memberof Cour
     */
    createdAt?: any;
    /**
     * 
     * @type {BaseModelpropertiesupdatedAt}
     * @memberof Cour
     */
    updatedAt?: any;
    /**
     * 
     * @type {BaseModelpropertiesdeletedAt}
     * @memberof Cour
     */
    deletedAt?: any;
}