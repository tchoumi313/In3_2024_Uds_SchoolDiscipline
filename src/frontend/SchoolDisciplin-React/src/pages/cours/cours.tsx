//import React from 'react';
import { AgGridReact } from 'ag-grid-react';
import { ColDef } from 'ag-grid-community';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';

const Cours = () => {
    const defaultColDef = {
        sortable: true,
        filter: true,
        resizable: true,
        flex: 1
    };

    const columnDefs: ColDef[] = [
        { headerName: 'Libellé', field: 'libelle' },
        { headerName: 'Professeur', field: 'professeur' },
        { headerName: 'Classes', field: 'classes' },
        { headerName: 'Date du cours', field: 'dateDuCours' },
        { headerClass: 'Heure de debut', field: 'heureDeDebut' },
        { headerName: 'Heure de fin', field: 'heureDeFin' }
    ];

    const rowData = [
        { libelle: 'Géographie', professeur: 'Professeur1', classes: 'TLE A1', dateDuCours: '2023-01-04 ', heureDeDebut: '2023-01-05', heureDeFin: '2023-01-05' },
        { libelle: 'Histoire', professeur: 'Professeur2', classes: 'TLE A2', dateDuCours: '2023-01-04', heureDeDebut: '2023-01-05', heureDeFin: '2023-01-05' },
        { libelle: 'Mathématiques', professeur: 'Professeur3', classes: 'TLE C1', dateDuCours: '2023-01-04', heureDeDebut: '2023-01-05', heureDeFin: '2023-01-05' }
    ];

    return (
        <div>
            <div style={{ marginBottom: '10px', display: 'flex', justifyContent: 'space-between' }}>
                <h2>Cours</h2>
                <div>
                    <button style={{ backgroundColor: 'green', color: 'white' }}>Nouveau</button>
                </div>

            </div>

            <div style={{ marginTop: '10px' }}>
                <button style={{ backgroundColor: 'green', color: 'white', marginRight: '10px' }}>Export en Excel</button>
                <button style={{ backgroundColor: 'lightblue', color: 'white', marginRight: '10px' }}>Rafraîchir</button>
                <button style={{ backgroundColor: 'green', color: 'white', marginRight: '10px' }}>Voir</button>
                <button style={{ backgroundColor: 'blue', color: 'white', marginRight: '10px' }}>Modifier</button>
                <button style={{ backgroundColor: 'red', color: 'white' }}>Supprimer</button>
            </div>


            <div className="ag-theme-alpine" style={{ height: '400px', width: '100%' }}>
                <AgGridReact
                    defaultColDef={defaultColDef}
                    pagination={true}
                    paginationPageSize={10}
                    columnDefs={columnDefs}
                    rowData={rowData}
                />
            </div>

           
        </div>
    );
};

export default Cours;
