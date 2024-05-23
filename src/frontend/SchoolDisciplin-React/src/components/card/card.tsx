const Card = () => {
    const CARD = [
        {
          id: 0,
          name: "Abscenses",
          numb: 10,
          style: "width: 10%",
          icon: "fas fa-people-carry-box",
          description: "Nombre total d'Abscences'"
        },
        {
          id: 1,
          name: "Retard",
          numb: 20,
          style: "width: 20%",
          icon: "fas fa-tags",
          description: "Nombre total de Retardataires"
        },
        {
          id: 2,
          name: "Convocations",
          numb: 40,
          style: "width: 40%",
          icon: "fab fa-dropbox",
          description: "Nombre total de Convocations"
        },
        {
          id: 3,
          name: "indiscipline",
          numb: 60,
          style: "width: 60%",
          icon: "fab fa-hive",
          description: "Nombre total d'indiciplines"
        },
      ];
    
      return (
        <div className="flex flex-wrap">
          {CARD.map((card) => (
            <div key={card.id} className="w-72 h-36 flex bg-slate-100 shadow-xl hover:border-b-4 hover:border-l-4 hover:border-r-4 hover:border-t-4 hover:border-x-sky-800 hover:border-y-sky-800 hover:opacity-80 transition-opacity hover:rounded-lg hover:transition-all m-2">
              <div className="w-20 px-3 bg-sky-700 items-center justify-center flex">
                <i className={`${card.icon} text-5xl`}></i>
              </div>
              <div className="pl-3">
                <p className="text-lg pt-2 flex">
                  {card.name}
                </p>
                <p>
                  {card.numb}
                </p>
                <hr className="mr-12 mb-1" />
                <small className="text-slate-600 mb-2">
                  {card.description}
                </small>
              </div>
            </div>
          ))}
        </div>
      );
    }

export default Card;