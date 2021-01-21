import axios from 'axios';
import {logout} from '../services/auth';


let Axios = axios.create({
  baseURL: 'http://localhost:8080/api',
  /* other custom settings */
});

Axios.interceptors.request.use(
  function success(config){
    let token = window.localStorage.getItem("token");
    if(token){
      config.headers["Authorization"] = `Bearer ${token}`;
    }

    return config;
  }
);

Axios.interceptors.response.use(
  function succes(response){
    return response;
  },
  function fail(error){
    let token = window.localStorage.getItem("token");

    if(token){
      if(error.response && error.response.status === 403){
        logout();
      }
    }

    throw error;
  }
)

export default Axios;