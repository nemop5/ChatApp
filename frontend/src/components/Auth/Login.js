import React from "react";
import { Grid, Form, Segment, Button, Header, Message, Icon} from "semantic-ui-react";
import { Link } from "react-router-dom";
import jwtDecode from 'jwt-decode';
import Axios from "../../apis/Axios";

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      errors: [],
      loading: false
    };

  }

  async login(username, password) {
    let dto = {
      username: username,
      password: password
    };
    try {
      let result = await Axios.post("/users/auth", dto)
      console.log(jwtDecode(result.data));

      window.localStorage.setItem("token", result.data);
      window.localStorage.setItem("username", jwtDecode(result.data).sub);
      window.location.reload()
    } catch(error) {
      console.log(error);
      this.setState({
        errors: this.state.errors.concat(error),
        loading: false
      });
    }
  }

  displayErrors = errors =>
    errors.map((error, i) => <p key={i}>{error.message}</p>);

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    if (this.isFormValid(this.state)) {
      this.setState({ errors: [], loading: true });
      this.login(this.state.username, this.state.password);
    }
  };

  isFormValid = ({ username, password }) => username && password;

  handleInputError = (errors, inputName) => {
    return errors.some(error => error.message.toLowerCase().includes(inputName))
      ? "error"
      : "";
  };

  render() {
    const { username, password, errors, loading } = this.state;

    return (
      <Grid textAlign="center" verticalAlign="middle" className="app">
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as="h1" icon color="violet" textAlign="center">
            <Icon name="code branch" color="violet" />
            Login to ChatApp
          </Header>
          <Form onSubmit={this.handleSubmit} size="large">
            <Segment stacked>
              <Form.Input
                fluid
                name="username"
                icon="user"
                iconPosition="left"
                placeholder="Username"
                onChange={this.handleChange}
                value={username}
                className={this.handleInputError(errors, "username")}
                type="username"
              />

              <Form.Input
                fluid
                name="password"
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                onChange={this.handleChange}
                value={password}
                className={this.handleInputError(errors, "password")}
                type="password"
              />

              <Button
                disabled={loading}
                className={loading ? "loading" : ""}
                color="violet"
                fluid
                size="large"
              >
                Submit
              </Button>
            </Segment>
          </Form>
          {errors.length > 0 && (
            <Message error>
              <h3>Error</h3>
              {this.displayErrors(errors)}
            </Message>
          )}
          <Message>
            Don't have an account? <Link to="/register">Register</Link>
          </Message>
        </Grid.Column>
      </Grid>
    );
  }
}

export default Login;
