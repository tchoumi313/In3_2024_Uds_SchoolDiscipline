import React, { useState } from 'react';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css'; // CSS obligatoire
import 'ag-grid-community/styles/ag-theme-quartz.css'; // Thème Alpine optionnel (ou un autre thème de votre choix)
import { ColDef } from 'ag-grid-community'; // Importez le type ColDef
import { Document, Page, Text, View, PDFViewer } from '@react-pdf/renderer';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faPlus,
    faFilePdf,
    faSync,
    faEdit,
    faTrashAlt
} from '@fortawesome/free-solid-svg-icons';

// Interface pour la structure des données
interface StudentData {
    Index: number;
    Photo: string;
    Prenom: string;
    Nom: string; // Supposant que "Nom" est censé être numérique (par exemple, ID de l'élève)
    Statut: boolean;
    Frais_Scolaire: string;
    Sexe: string;
    Salle: string;
    Serie: string;
}

const Eleve: React.FC = () => {
    // Utilisez l'état pour gérer les données du tableau
    const [data, setData] = useState<StudentData[]>([
        { Index: 1, Photo: "Tesla", Prenom: "Model Y", Nom: "U d s", Statut: true, Frais_Scolaire: "impayée", Sexe: "Masculin", Salle: "Tle A1", Serie: "A" },
        { Index: 2, Photo: "Ford", Prenom: "F-Series", Nom: "U D s 1", Statut: false, Frais_Scolaire: "impayée", Sexe: "Masculin", Salle: "Tle A1", Serie: "A" },
        { Index: 3, Photo: "Toyota", Prenom: "Corolla", Nom: "U d s", Statut: false, Frais_Scolaire: "impayée", Sexe: "Masculin", Salle: "Tle A1", Serie: "A" },
    ]);

    // Définissez les colonnes avec les en-têtes appropriés
    const columnDefs: ColDef[] = [
        { headerName: 'Index', field: 'Index', width: 100, checkboxSelection: true }, // Colonne d'index avec sélection par checkbox
        { headerName: 'Photo', field: 'Photo', width: 100 },
        { headerName: 'Prénom', field: 'Prenom' },
        { headerName: 'Nom', field: 'Nom', width: 100 },
        { headerName: 'Statut', field: 'Statut', cellRenderer: (params) => params.value ? 'Actif' : 'Inactif' }, // Rendu personnalisé de la cellule pour le statut
        { headerName: 'Frais de Scolarité', field: 'Frais_Scolaire' },
        { headerName: 'Sexe', field: 'Sexe' },
        { headerName: 'Salle', field: 'Salle' },
        { headerName: 'Série', field: 'Serie' },
    ];

    const handleAddStudent = (newData: StudentData) => {
        setData((prevState) => [...prevState, newData]);
    };

    const [showModal, setShowModal] = useState(false);
    const [selectedRow, setSelectedRow] = useState<StudentData | null>(null);
    const [newStudent, setNewStudent] = useState<StudentData>({
        Index: 0,
        Photo: '',
        Prenom: '',
        Nom: 0,
        Statut: true,
        Frais_Scolaire: '',
        Sexe: '',
        Salle: '',
        Serie: ''
    });

    const handleAdd = () => {
        const newId = data.length ? Math.max(...data.map(item => item.Nom)) + 1 : 1;
        setData([...data, { ...newStudent, Nom: newId }]);
        setShowModal(false);
        setNewStudent({
            Index: 0,
            Photo: '',
            Prenom: '',
            Nom: '',
            Statut: true,
            Frais_Scolaire: '',
            Sexe: '',
            Salle: '',
            Serie: ''
        });
    };

    const handleExportPDF = () => {
        const doc = (
            <Document>
                <Page size="A4">
                    <View>
                        <Text>ELEVES</Text>
                        {data.map((student, index) => (
                            <View key={index} style={{ marginBottom: 10 }}>
                                <Text>Index: {student.Index}</Text>
                                <Text>Photo: {student.Photo}</Text>
                                <Text>Prénom: {student.Prenom}</Text>
                                <Text>Nom: {student.Nom}</Text>
                                <Text>Statut: {student.Statut ? 'Actif' : 'Inactif'}</Text>
                                <Text>Frais de Scolarité: {student.Frais_Scolaire}</Text>
                                <Text>Sexe: {student.Sexe}</Text>
                                <Text>Salle: {student.Salle}</Text>
                                <Text>Série: {student.Serie}</Text>
                            </View>
                        ))}
                    </View>
                </Page>
            </Document>
        );

        PDFViewer.showPdf(doc);
    };

    const handleModify = () => {
        if (selectedRow) {
            setNewStudent(selectedRow);
            setShowModal(true);
        }
    };

    const handleDelete = () => {
        if (selectedRow) {
            setData(data.filter(row => row.Nom !== selectedRow.Nom));
            setSelectedRow(null);
        }
    };

    return (
        <div className="ag-theme-quartz w-full h-96 mx-auto">
            <div className='flex justify-between items-center mr-4'>
                <p className='dark:text-white text-gray-800 mb-10 pb-8 text-bold font-mono text-2xl'>ELEVE</p>
                <button className='bg-green-500 text-white add flex items-center' onClick={() => setShowModal(true)}>
                    <FontAwesomeIcon icon={faPlus} className='mr-2' />
                    Nouveau
                </button>
            </div>
            <div className='buttons flex gap-4 mb-4'>
                <button className='export bg-green-500 text-white flex items-center' onClick={handleExportPDF}>
                    <FontAwesomeIcon icon={faFilePdf} className='mr-2' />
                    Export en PDF
                </button>
                <button className='rafraichir bg-blue-200 text-white flex items-center' onClick={() => window.location.reload()}>
                    <FontAwesomeIcon icon={faSync} className='mr-2' />
                    Rafraîchir
                </button>
                <button className='modifier bg-blue-500 text-white flex items-center' onClick={handleModify}>
                    <FontAwesomeIcon icon={faEdit} className='mr-2' />
                    Modifier
                </button>
                <button className='supprimer bg-red-500 text-white flex items-center' onClick={handleDelete}>
                    <FontAwesomeIcon icon={faTrashAlt} className='mr-2' />
                    Supprimer
                </button>
            </div>

            <AgGridReact
                rowData={data}
                columnDefs={columnDefs}
                pagination={true}
                paginationPageSize={500}
                paginationPageSizeSelector={[200, 500, 1000]}
                onRowClicked={(event) => setSelectedRow(event.data)}
            />

            {showModal && (
                <div className="fixed inset-0 text-gray-800 bg-black bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white dark:bg-slate-50 p-4 rounded shadow-lg w-full max-w-md mx-2">
                        <h2 className="text-lg font-bold mb-4">Nouveau Étudiant</h2>
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="file"
                            placeholder="Photo"
                            value={newStudent.Photo}
                            onChange={(e) => setNewStudent({ ...newStudent, Photo: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Prénom"
                            value={newStudent.Prenom}
                            onChange={(e) => setNewStudent({ ...newStudent, Prenom: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Nom"
                            value={newStudent.Nom}
                            onChange={(e) => setNewStudent({ ...newStudent, Nom: Number(e.target.value) })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Frais de Scolarité"
                            value={newStudent.Frais_Scolaire}
                            onChange={(e) => setNewStudent({ ...newStudent, Frais_Scolaire: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Sexe"
                            value={newStudent.Sexe}
                            onChange={(e) => setNewStudent({ ...newStudent, Sexe: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Salle"
                            value={newStudent.Salle}
                            onChange={(e) => setNewStudent({ ...newStudent, Salle: e.target.value })}
                        />
                        <input
                            className="border p-2 mb-2 w-full bg-slate-100 text-gray-800"
                            type="text"
                            placeholder="Série"
                            value={newStudent.Serie}
                            onChange={(e) => setNewStudent({ ...newStudent, Serie: e.target.value })}
                        />
                        <div className="flex justify-end">
                            <button className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 mr-2" onClick={() => setShowModal(false)}>Annuler</button>
                            <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600" onClick={handleAdd}>Enregistrer</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Eleve;
