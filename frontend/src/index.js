import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import NotFound from "./components/NotFound";
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import Spinner from "./Spinner";

import "semantic-ui-css/semantic.min.css";

import { BrowserRouter as Router, Switch, Route, withRouter} from "react-router-dom";

import { createStore } from "redux";
import { Provider, connect } from "react-redux";
import { composeWithDevTools } from "redux-devtools-extension";
import rootReducer from "./reducers";
import { setUser } from "./actions";



const store = createStore(rootReducer, composeWithDevTools());

class Root extends React.Component {

  componentDidMount() {
    let token = window.localStorage.getItem("token");
    let user = window.localStorage.getItem("username");

    if(token){
      this.props.setUser(user);
      this.props.history.push("/");
    }
  }

  render() {
    return this.props.isLoading ? (
      <Spinner />
    ) : (
        <Switch>
          <Route exact path="/" component={App} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <Route component={NotFound} />
        </Switch>
    );
  }
}

const mapStateFromProps = state => ({
  isLoading: state.user.isLoading
});

const RootWithAuth = withRouter(
  connect(
    mapStateFromProps,
    { setUser }
  )(Root)
);

ReactDOM.render(
  <Provider store={store}>
    <Router>
      <RootWithAuth />
    </Router>
  </Provider>,
  document.getElementById("root")
);
