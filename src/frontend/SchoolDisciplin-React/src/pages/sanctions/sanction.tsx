import React, { useState, useRef, useMemo } from 'react';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-quartz.css';
import { PDFDownloadLink, Document, Page, Text } from '@react-pdf/renderer';
import { ModuleRegistry } from '@ag-grid-community/core';
import { ClientSideRowModelModule } from '@ag-grid-community/client-side-row-model';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faPlus,
    faFilePdf,
    faSync,
    faSave,
    faTimes
} from '@fortawesome/free-solid-svg-icons';

ModuleRegistry.registerModules([ClientSideRowModelModule]);

const Sanction: React.FC = () => {
    const [rowData, setRowData] = useState([
        { id: 1, libelleSanction: "Retard", libelleFaute: "Non-respect des horaires", date: "2023-05-01", eleve: "Fredie J" },
        { id: 2, libelleSanction: "Absence", libelleFaute: "Absence injustifiée", date: "2023-05-02", eleve: "Vergez" },
        { id: 3, libelleSanction: "Insolence", libelleFaute: "Insolence envers un enseignant", date: "2023-05-03", eleve: "Senge" },
        { id: 4, libelleSanction: "Violence", libelleFaute: "Violence envers un camarade", date: "2023-05-04", eleve: "Jordan" },
        { id: 5, libelleSanction: "Retard", libelleFaute: "Non-respect des horaires", date: "2023-05-05", eleve: "Fredie J" },
    ]);

    const gridRef = useRef(null);

    const [colDefs, setColDefs] = useState([
        { field: "libelleSanction", headerName: "Libellé Sanction", filter: 'agTextColumnFilter', sortable: true, flex: 1 },
        { field: "libelleFaute", headerName: "Libellé Faute", filter: 'agTextColumnFilter', sortable: true, flex: 2 },
        { field: "date", headerName: "Date", filter: 'agDateColumnFilter', sortable: true, flex: 1 },
        { field: "eleve", headerName: "Élève", filter: 'agTextColumnFilter', sortable: true, flex: 1 },
    ]);

    const handleAdd = (newData) => {
        setRowData([...rowData, { ...newData, id: rowData.length + 1 }]);
    };

    const handleRefresh = () => {
        alert('Data refreshed');
    };

    const PdfDocument = ({ data }) => (
        <Document>
            <Page size="A4">
                <Text>Sanctions Report</Text>
                {data.map((item, index) => (
                    <Text key={index}>{item.libelleSanction} - {item.libelleFaute} - {item.date} - {item.eleve}</Text>
                ))}
            </Page>
        </Document>
    );

    const [showModal, setShowModal] = useState(false);
    const [newLibelleSanction, setNewLibelleSanction] = useState('');
    const [newLibelleFaute, setNewLibelleFaute] = useState('');
    const [newDate, setNewDate] = useState('');
    const [newEleve, setNewEleve] = useState('');

    const handleSaveNew = () => {
        handleAdd({ libelleSanction: newLibelleSanction, libelleFaute: newLibelleFaute, date: newDate, eleve: newEleve });
        setShowModal(false);
    };

    const defaultColDef = useMemo(() => {
        return {
            filter: 'agTextColumnFilter',
            floatingFilter: true,
            resizable: true,
        };
    }, []);

    return (
        <div className="h-full flex flex-col p-4 w-full">
            <div className="mb-4 flex justify-between items-center">
                <h2 className="text-lg font-bold dark:text-white text-gray-800 mb-10 pb-8 font-mono text-2xl">Sanctions</h2>
                <div className="flex gap-4">
                    <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 flex items-center" onClick={() => setShowModal(true)}>
                        <FontAwesomeIcon icon={faPlus} className='mr-2' />
                        Ajouter
                    </button>
                    <button className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 flex items-center" onClick={handleRefresh}>
                        <FontAwesomeIcon icon={faSync} className='mr-2' />
                        Rafraîchir
                    </button>
                    <PDFDownloadLink
                        document={<PdfDocument data={rowData} />}
                        fileName="sanctions_report.pdf"
                    >
                        {({ loading }) => (
                            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 flex items-center">
                                <FontAwesomeIcon icon={faFilePdf} className='mr-2' />
                                {loading ? 'Loading document...' : 'Exporter en PDF'}
                            </button>
                        )}
                    </PDFDownloadLink>
                </div>
            </div>
            <div className="ag-theme-quartz flex-grow w-full" style={{ height: 'calc(200vh - 900px)' }}>
                <AgGridReact
                    ref={gridRef}
                    rowData={rowData}
                    columnDefs={colDefs}
                    defaultColDef={defaultColDef}
                    pagination={true}
                    paginationPageSize={10}
                    domLayout='autoHeight'
                />
            </div>
            {showModal && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-4 rounded shadow-lg w-full max-w-md mx-2">
                        <h2 className="text-lg font-bold mb-4">Ajouter Nouvelle Sanction</h2>
                        <input
                            className="border p-2 mb-2 w-full"
                            type="text"
                            placeholder="Libellé Sanction"
                            value={newLibelleSanction}
                            onChange={(e) => setNewLibelleSanction(e.target.value)}
                        />
                        <input
                            className="border p-2 mb-2 w-full"
                            type="text"
                            placeholder="Libellé Faute"
                            value={newLibelleFaute}
                            onChange={(e) => setNewLibelleFaute(e.target.value)}
                        />
                        <input
                            className="border p-2 mb-2 w-full"
                            type="date"
                            placeholder="Date"
                            value={newDate}
                            onChange={(e) => setNewDate(e.target.value)}
                        />
                        <input
                            className="border p-2 mb-2 w-full"
                            type="text"
                            placeholder="Élève"
                            value={newEleve}
                            onChange={(e) => setNewEleve(e.target.value)}
                        />
                        <div className="flex justify-end">
                            <button className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 mr-2 flex items-center" onClick={() => setShowModal(false)}>
                                <FontAwesomeIcon icon={faTimes} className='mr-2' />
                                Annuler
                            </button>
                            <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 flex items-center" onClick={handleSaveNew}>
                                <FontAwesomeIcon icon={faSave} className='mr-2' />
                                Enregistrer
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Sanction;
