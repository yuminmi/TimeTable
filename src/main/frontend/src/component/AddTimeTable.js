export default function AddTimeTable ({isOpen, closeModal}) {
    return (
        <div style={{display:isOpen?"block": "none",
                     position: "fixed",
                     top: 0,
                     left: 0,
                     width: "100vw",
                     height: "100vh",
                     backgroundColor: "rgba(0,0,0,0.35)"}}>
            <div style={{
                position: "absolute",
                top: "50%",
                left: "50%",
                transform: "translate(-50%, -50%)",
                width: "463px",
                height: "478px",
                backgroundColor: "white",
                borderRadius: "20px",
                border: "solid 1px"
            }}>
                <div style={{padding: "50px"}}>
                    <div>
                        <h2 style={{display: "inline"}}>Add</h2>
                        <button onClick={closeModal} style={{ border: "none",
                                                              outline: "none",
                                                              backgroundColor: "inherit",
                                                              cursor: "pointer",
                                                              fontSize: "x-large",
                                                              marginRight: "0px"}}>X</button>
                    </div>
                     <hr/>
                     <form>
                          <p>subject : </p>
                          <input type="text"></input>
                          <p>teacher : </p>
                          <input type="text"></input>
                          <p>location : </p>
                          <input type="text"></input>
                          <p>time : </p>
                          <input type="text"></input>
                          <button>ADD</button>
                     </form>
                </div>

            </div>
        </div>
    );
}