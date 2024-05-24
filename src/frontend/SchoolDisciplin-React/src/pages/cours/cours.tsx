import React, { useState, useMemo } from 'react';
import { AgGridReact } from 'ag-grid-react';
import { ColDef } from 'ag-grid-community';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import { PDFDownloadLink, Document, Page, Text, StyleSheet } from '@react-pdf/renderer';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faPlus,
    faFilePdf,
    faSync,
    faEdit,
    faTrashAlt
} from '@fortawesome/free-solid-svg-icons';

const Cours = () => {
    const [rowData, setRowData] = useState([
        { id: 1, libelle: 'Géographie', professeur: 'Professeur1', classes: 'TLE A1', dateDuCours: '2023-01-04', heureDeDebut: '08:00', heureDeFin: '10:00' },
        { id: 2, libelle: 'Histoire', professeur: 'Professeur2', classes: 'TLE A2', dateDuCours: '2023-01-04', heureDeDebut: '10:00', heureDeFin: '12:00' },
        { id: 3, libelle: 'Mathématiques', professeur: 'Professeur3', classes: 'TLE C1', dateDuCours: '2023-01-04', heureDeDebut: '13:00', heureDeFin: '15:00' }
    ]);

    const [selectedRow, setSelectedRow] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [newCourse, setNewCourse] = useState({ libelle: '', professeur: '', classes: '', dateDuCours: '', heureDeDebut: '', heureDeFin: '' });

    const defaultColDef = useMemo(() => ({
        sortable: true,
        filter: true,
        resizable: true,
        flex: 1
    }), []);

    const columnDefs: ColDef[] = [
        { headerName: 'Index', valueGetter: 'node.rowIndex + 1', flex: 0.5 },
        { headerCheckboxSelection: true, checkboxSelection: true, flex: 0.5 },
        { headerName: 'Libellé', field: 'libelle' },
        { headerName: 'Professeur', field: 'professeur' },
        { headerName: 'Classes', field: 'classes' },
        { headerName: 'Date du cours', field: 'dateDuCours' },
        { headerName: 'Heure de début', field: 'heureDeDebut' },
        { headerName: 'Heure de fin', field: 'heureDeFin' }
    ];

    const handleAdd = () => {
        const newId = rowData.length ? Math.max(...rowData.map(item => item.id)) + 1 : 1;
        setRowData([...rowData, { ...newCourse, id: newId }]);
        setShowModal(false);
        setNewCourse({ libelle: '', professeur: '', classes: '', dateDuCours: '', heureDeDebut: '', heureDeFin: '' });
    };

    const handleEdit = () => {
        setRowData(rowData.map(row => (row.id === selectedRow.id ? newCourse : row)));
        setShowModal(false);
        setSelectedRow(null);
        setNewCourse({ libelle: '', professeur: '', classes: '', dateDuCours: '', heureDeDebut: '', heureDeFin: '' });
    };

    const handleDelete = () => {
        setRowData(rowData.filter(row => row.id !== selectedRow.id));
        setSelectedRow(null);
    };

    const styles = StyleSheet.create({
        page: {
            padding: 30
        },
        section: {
            margin: 10,
            padding: 10,
            fontSize: 12
        },
        title: {
            fontSize: 18,
            textAlign: 'center',
            marginBottom: 20
        }
    });

    const PdfDocument = ({ data }) => (
        <Document>
            <Page size="A4" style={styles.page}>
                <Text style={styles.title}>Liste des Cours</Text>
                {data.map((item, index) => (
                    <Text key={index} style={styles.section}>{item.libelle} - {item.professeur} - {item.classes} - {item.dateDuCours} - {item.heureDeDebut} - {item.heureDeFin}</Text>
                ))}
            </Page>
        </Document>
    );

    return (
        <div className="h-full flex flex-col p-4 w-full">
            <div className="mb-4 flex justify-between items-center">
                <h2 className="text-lg font-bold dark:text-white text-gray-800 mb-10 pb-8 font-mono text-2xl">Cours</h2>
                <div className="flex gap-4">
                    <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 flex items-center" onClick={() => { setShowModal(true); setSelectedRow(null); }}>
                        <FontAwesomeIcon icon={faPlus} className='mr-2' />
                        Nouveau
                    </button>
                    
                </div>
            </div>

            <div className="mb-4 flex justify-between items-center">
                <PDFDownloadLink
                    document={<PdfDocument data={rowData} />}
                    fileName="cours_report.pdf"
                >
                    {({ loading }) => (
                        <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 flex items-center">
                            <FontAwesomeIcon icon={faFilePdf} className='mr-2' />
                            {loading ? 'Loading document...' : 'Export en PDF'}
                        </button>
                    )}
                </PDFDownloadLink>
                {selectedRow && (
                        <>
                            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 flex items-center" onClick={() => { setShowModal(true); setNewCourse(selectedRow); }}>
                                <FontAwesomeIcon icon={faEdit} className='mr-2' />
                                Modifier
                            </button>
                            <button className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 flex items-center" onClick={handleDelete}>
                                <FontAwesomeIcon icon={faTrashAlt} className='mr-2' />
                                Supprimer
                            </button>
                        </>
                    )}
                <button className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 flex items-center" onClick={() => window.location.reload()}>
                    <FontAwesomeIcon icon={faSync} className='mr-2' />
                    Rafraîchir
                </button>
            </div>

            <div className="ag-theme-alpine flex-grow w-full h-96">
                <AgGridReact
                    defaultColDef={defaultColDef}
                    pagination={true}
                    paginationPageSize={10}
                    columnDefs={columnDefs}
                    rowData={rowData}
                    onRowClicked={(event) => setSelectedRow(event.data)}
                />
            </div>

            {showModal && (
                <div className="fixed inset-0 text-gray-800 bg-black bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white dark:bg-slate-50 p-4 rounded shadow-lg w-full max-w-md mx-2">
                        <h2 className="text-lg font-bold mb-4">{selectedRow ? 'Modifier Cours' : 'Nouveau Cours'}</h2>
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Libellé"
                            value={newCourse.libelle}
                            onChange={(e) => setNewCourse({ ...newCourse, libelle: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Professeur"
                            value={newCourse.professeur}
                            onChange={(e) => setNewCourse({ ...newCourse, professeur: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Classes"
                            value={newCourse.classes}
                            onChange={(e) => setNewCourse({ ...newCourse, classes: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="date"
                            placeholder="Date du cours"
                            value={newCourse.dateDuCours}
                            onChange={(e) => setNewCourse({ ...newCourse, dateDuCours: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="time"
                            placeholder="Heure de début"
                            value={newCourse.heureDeDebut}
                            onChange={(e) => setNewCourse({ ...newCourse, heureDeDebut: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="time"
                            placeholder="Heure de fin"
                            value={newCourse.heureDeFin}
                            onChange={(e) => setNewCourse({ ...newCourse, heureDeFin: e.target.value })}
                        />
                        <div className="flex justify-end">
                            <button className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 mr-2" onClick={() => setShowModal(false)}>Annuler</button>
                            <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600" onClick={selectedRow ? handleEdit : handleAdd}>Enregistrer</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Cours;
