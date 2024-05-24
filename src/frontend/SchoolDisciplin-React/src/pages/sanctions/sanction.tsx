import React, { useState, useRef, useMemo } from 'react';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-quartz.css';
import { PDFDownloadLink, Document, Page, Text } from '@react-pdf/renderer';
import { ModuleRegistry } from '@ag-grid-community/core';
import { ClientSideRowModelModule } from '@ag-grid-community/client-side-row-model';

ModuleRegistry.registerModules([ClientSideRowModelModule]);

const Sanction: React.FC = () => {
    const [rowData, setRowData] = useState([
        { id: 1, make: "Tesla", model: "Model Y", price: 64950, electric: true },
        { id: 2, make: "Ford", model: "F-Series", price: 33850, electric: false },
        { id: 3, make: "Toyota", model: "Corolla", price: 29600, electric: false },
        { id: 4, make: "Honda", model: "Civic", price: 21500, electric: false },
        { id: 5, make: "Chevrolet", model: "Bolt", price: 36620, electric: true },
        { id: 6, make: "Nissan", model: "Leaf", price: 31400, electric: true },
        { id: 7, make: "BMW", model: "i3", price: 44450, electric: true },
        { id: 8, make: "Hyundai", model: "Kona", price: 37950, electric: true },
        { id: 9, make: "Audi", model: "e-tron", price: 65950, electric: true },
        { id: 10, make: "Volkswagen", model: "ID.4", price: 39850, electric: true },
    ]);

    const gridRef = useRef(null);

    const [colDefs, setColDefs] = useState([
        {
            field: "make", filter: 'agTextColumnFilter', sortable: true, checkboxSelection: true, editable: true,
            cellEditor: 'agSelectCellEditor',
            cellEditorParams: {
                values: ["Tesla", "Ford", "Toyota", "Mercedes", "Fiat", "Nissan", "Vauxhall", "Volvo", "Jaguar"],
            },
        },
        { field: "model", filter: 'agTextColumnFilter', sortable: true },
        { field: "price", filter: 'agNumberColumnFilter', sortable: true },
        { field: "electric", cellRenderer: (params) => params.value ? 'Yes' : 'No', filter: 'agTextColumnFilter', sortable: true },
        {
            headerName: "Actions",
            field: "id",
            cellRendererFramework: (params) => (
                <div className="flex space-x-2">
                    <button className="bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600" onClick={() => handleEdit(params.data)}>Edit</button>
                    <button className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600" onClick={() => handleDelete(params.data.id)}>Delete</button>
                </div>
            )
        }
    ]);

    const handleAdd = (newData) => {
        setRowData([...rowData, { ...newData, id: rowData.length + 1 }]);
    };

    const handleEdit = (data) => {
        alert(`Editing: ${JSON.stringify(data)}`);
    };

    const handleDelete = (id) => {
        setRowData(rowData.filter(row => row.id !== id));
    };

    const handleRefresh = () => {
        alert('Data refreshed');
    };

    const PdfDocument = ({ data }) => (
        <Document>
            <Page size="A4">
                <Text>Sanctions Report</Text>
                {data.map((item, index) => (
                    <Text key={index}>{item.make} {item.model} - ${item.price} - {item.electric ? 'Yes' : 'No'}</Text>
                ))}
            </Page>
        </Document>
    );

    const [showModal, setShowModal] = useState(false);
    const [newMake, setNewMake] = useState('');
    const [newModel, setNewModel] = useState('');
    const [newPrice, setNewPrice] = useState('');
    const [newElectric, setNewElectric] = useState(false);

    const handleSaveNew = () => {
        handleAdd({ make: newMake, model: newModel, price: Number(newPrice), electric: newElectric });
        setShowModal(false);
    };

    const defaultColDef = useMemo(() => {
        return {
            filter: 'agTextColumnFilter',
            floatingFilter: true,
        };
    }, []);

    return (
        <div className="flex flex-wrap">
            <div className="mb-4 flex justify-between items-center p-4 bg-gray-100 border-b border-gray-300">
                <div>
                    <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mr-2" onClick={() => setShowModal(true)}>Add</button>
                    <button className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 mr-2" onClick={handleRefresh}>Refresh</button>
                    <PDFDownloadLink
                        document={<PdfDocument data={rowData} />}
                        fileName="sanctions_report.pdf"
                    >
                        {({ loading }) => (
                            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                                {loading ? 'Loading document...' : 'Export to PDF'}
                            </button>
                        )}
                    </PDFDownloadLink>
                </div>
            </div>
            <div className="ag-theme-quartz" style={{ width: '100%' }}>
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
                <div className="fixed inset-0 bg-slate-200 bg-opacity-50 flex items-center justify-center">
                    <div className="bg-slate-500 p-4 rounded shadow-lg w-1/3 text-black">
                        <h2 className="text-lg font-bold mb-4">Add New Sanction</h2>
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100"
                            type="text"
                            placeholder="Make"
                            value={newMake}
                            onChange={(e) => setNewMake(e.target.value)}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100"
                            type="text"
                            placeholder="Model"
                            value={newModel}
                            onChange={(e) => setNewModel(e.target.value)}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100"
                            type="number"
                            placeholder="Price"
                            value={newPrice}
                            onChange={(e) => setNewPrice(e.target.value)}
                        />
                        <label className="flex items-center mb-4">
                            <input
                                className="mr-2"
                                type="checkbox"
                                checked={newElectric}
                                onChange={(e) => setNewElectric(e.target.checked)}
                            />
                            Electric
                        </label>
                        <div className="flex justify-end">
                            <button className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 mr-2" onClick={() => setShowModal(false)}>Cancel</button>
                            <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600" onClick={handleSaveNew}>Save</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Sanction;
