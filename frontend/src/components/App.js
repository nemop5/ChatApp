import React, { Component } from "react";
import "./App.css";

class App extends Component {

  componentDidMount() {
    let token = window.localStorage.getItem("token");

    if(!token){
      this.props.history.push("/login");
    }
  }

  render() {

    return <div>App</div>;
  }
}

export default App;
