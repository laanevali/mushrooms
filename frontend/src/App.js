import { useEffect, useRef, useState } from 'react';
import './App.css';

function App() {
  const [mushrooms, setMushrooms] = useState([]);
  const latitudeRef = useRef();
  const longitudeRef = useRef();
  const commentRef = useRef();


  useEffect(() => {
    fetch("http://localhost:8080/mushrooms")
      .then(res => res.json())
      .then(json => {
        if (Array.isArray(json)) {
          setMushrooms(json);
        } else {
          console.error("Invalid response from the API:", json);
          setMushrooms([]);
        }
      })
      .catch(error => {
        console.error("Error fetching mushrooms:", error);
        setMushrooms([]);
      });
  }, [])

  function deleteMushroom(index) {
    fetch("http://localhost:8080/mushrooms/" + index, {"method": "DELETE"})
      .then(res => res.json())
      .then(json => setMushrooms(json));
  }

  function addMushroom() {
    const newMushroom = {
      "latitude": parseFloat(latitudeRef.current.value),
      "longitude": parseFloat(longitudeRef.current.value),
      "comment": commentRef.current.value
    }
    fetch("http://localhost:8080/add-mushrooms", {"method": "POST", "body": JSON.stringify(newMushroom), "headers": {"Content-Type": "application/json"}})
      .then(res => res.json())
      .then(json => setMushrooms(json));
  }



  return (
    <div className="App">
      <label>Latitude</label> <br/>
      <input ref={latitudeRef} type="float"/> <br/>
      <label>Longitude</label> <br/>
      <input ref={longitudeRef} type="float"/> <br/>
      <label>Comment</label> <br/>
      <input ref={commentRef} type="text"/> <br/>
      <button onClick={() => addMushroom()}>Add</button>
      {console.log("Mushrooms:", mushrooms)}
      {mushrooms.map((mushroom, index) =>
        <div key={index}>
          ID: {mushroom.id}{"\t"}
          Latitude: {mushroom.latitude !== undefined ? mushroom.latitude.tofixed(4) : 'N/A'}{"\t"}
          Longitude: {mushroom.longitude !== undefined ? mushroom.longitude.toFixed(4) : 'N/A'}{"\t"}
          Comment: {mushroom.comment}{"\t"}
          <button onClick={() => deleteMushroom(index)}>X</button>
        </div>)}
    </div>
  );
}

export default App;
