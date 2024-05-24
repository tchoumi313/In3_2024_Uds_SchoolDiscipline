import React, { useState } from 'react';
import DataTable from 'react-data-table-component';

function Convocation() {
    const columns = [
        {
            name: 'Nom_eleve',
            selector: row => row.name,
            sortable: true,
        },
        {
            name: 'Email_parent',
            selector: row => row.email,
            sortable: true,
        },
        {
            name: 'Motifs_de_convocation',
            selector: row => row.motif,
            sortable: true,
        },
        {
            name: 'Sanction',
            selector: row => row.sanction,
            sortable: true,
        },
        {
            name: 'Age',
            selector: row => row.age,
            sortable: true,
        },
    ];

    const data = [
        {
            id: 1,
            name: 'Ken',
            email: 'kenparent@gmail.com',
            age: '33',
            sanction: 'vider la poubelle',
            motif: 'vole',
        },
        {
            id: 2,
            name: 'Fredie',
            email: 'kenparent@gmail.com',
            age: '33',
            sanction: 'vider la poubelle',
            motif: 'vole',
        },
        {
            id: 3,
            name: 'Jaures',
            email: 'kenparent@gmail.com',
            age: '33',
            sanction: 'vider la poubelle',
            motif: 'vole',
        },
        {
            id: 4,
            name: 'Vergez',
            email: 'kenparent@gmail.com',
            age: '33',
            sanction: 'vider la poubelle',
            motif: 'vole',
        },
        {
            id: 5,
            name: 'Ken',
            email: 'kenparent@gmail.com',
            age: '33',
            sanction: 'vider la poubelle',
            motif: 'vole',
        },
    ];

    const [records, setRecords] = useState(data);

    function handleFilter(event) {
        const newData = data.filter(row => {
            return row.name.toLowerCase().includes(event.target.value.toLowerCase());
        });
        setRecords(newData);
    }

    return (
        <div className='container mt-5'>
            <div className='text-end'>
                <input type="text" onChange={handleFilter} />
            </div>
            <DataTable
                columns={columns}
                data={records}
                selectableRows
                fixedHeader
                pagination
            />
        </div>
    );
}

export default Convocation;
