function Modale() {


  return(
    <>
      <div className='flex justify-between items-center new'>
        <p>creer une nouvelle eleve</p>
        <hr />

        <div>
          <div className='info'>
            <label htmlFor="">First name</label>
            <input type="text" placeholder='Enter your first name' />
          </div>

          <div className='info'>
            <label htmlFor="">last name</label>
            <input type="text" placeholder='Enter your last name' />
          </div>

          <div className='info'>
            <label htmlFor="">Tel</label>
            <input type="tel" placeholder='Le numero de telephone' />
          </div>

          <div className='info'>
            <label htmlFor="">Salle de classe</label>
            <input type="text" placeholder="Saisir le nom d\'une salle" />
          </div>

          <div className='info'>
            <label htmlFor="">Sexe</label>
            <input type="text" placeholder='Masculin' />
          </div>

          <div className='info'>
            <label htmlFor="">Status</label>
            <input type="text" placeholder='Redoublant(e)' />
          </div>

          <div className='info'>
            <label htmlFor="">Email</label>
            <input type="email" placeholder='Enter your Email Adress' />
          </div>

          <div className='info'>
            <label htmlFor="">Solvable</label>
            <input type="text" placeholder='impaye' />
          </div>

          <div className='info'>
            <label htmlFor="">Date de Naissance</label>
            <input type="date" placeholder='jj//mm/aaaa' />
          </div>

          <div className='info'>
            <label htmlFor="">Lieu de Naissance</label>
            <input type="text" placeholder='Enter your Place of Birth' />
          </div>


        </div>
      </div>

  </>
  )

}
export default Modale;