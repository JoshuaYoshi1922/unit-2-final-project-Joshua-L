import React from "react";

//maybe include
function MorePokeInfo({closeModal}) {
  return(
  <div className="moreBG">
    <div className="moreCon">
      <div className="moreCloseBTN">
        <button onClick={() => closeModal(false)}>
          X
        </button>

      </div>
      <div className="title">
        <h1>ugh</h1>
        <div className="body">
          <h2>uggg</h2>
          <div className="footer">
          
          </div>

        </div>
      </div>
    </div>

  </div>
  )

}

export default MorePokeInfo;
