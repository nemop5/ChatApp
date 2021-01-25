import React from "react";
import { Grid, Form, Segment, Button, Header, Message, Icon } from "semantic-ui-react";
import { Link } from "react-router-dom";
import Axios from "../../apis/Axios";
import md5 from "md5";

class Register extends React.Component {
  constructor(props) {
    super(props);

    let user = {
      firstName: "",
      lastName: "",
      username: "",
      eMail: "",
      password: "",
      repeatedPassword: "",
    };
  
    this.state = {
      user: user,
      errors: [],
      loading: false
    };
  }

  isFormValid = () => {
    let errors = [];
    let error;

    if (this.isFormEmpty(this.state.user)) {
      error = { message: "Fill in all fields"};
      this.setState({ errors: errors.concat(error) });
      return false;
    } else if (!this.isPasswordValid(this.state.user)) {
      error = { message: "Password is invalid" };
      this.setState({ errors: errors.concat(error) });
      return false;
    } else {
      return true;
    }
  }

  isFormEmpty = ({ firstName, lastName, username, eMail, password, repeatedPassword }) => {
    return (
      !firstName.length ||
      !lastName.length ||
      !username.length ||
      !eMail.length ||
      !password.length ||
      !repeatedPassword.length
    );
  }

  isPasswordValid = ({ password, repeatedPassword }) => {
    if (password.length < 6 || repeatedPassword.length < 6) {
      return false;
    } else if (password !== repeatedPassword) {
      return false;
    } else {
      return true;
    }
  }

  displayErrors = errors =>
  errors.map((error, i) => <p key={i}>{error.message}</p>);

  handleChange = event => {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let user = this.state.user;
    user[name] = value;

    this.setState({ user: user });
  };


  async handleSubmit(event) {
    event.preventDefault();
    if(this.isFormValid()) {
      try{
        await Axios.post("/users/", this.state.user);
  
        let user = {
          firstName: "",
          lastName: "",
          username: "",
          eMail: "",
          password: "",
          repeatedPassword: "",
        };
  
        this.setState({ user: user });
  
      }catch(error){
        console.log(error);
        this.setState({
          errors: this.state.errors.concat(error),
          loading: false
        });
      }
    }
  };

  handleInputError = (errors, inputName) => {
    return errors.some(error => error.message.toLowerCase().includes(inputName))
      ? "error"
      : "";
  };

  render() {
    const { errors } = this.state

    return (
      <Grid textAlign="center" verticalAlign="middle" className="app">
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as="h2" icon color="violet" textAlign="center">
            <Icon name="user plus" color="violet" />
            Register for ChatApp
          </Header>
          <Form onSubmit={(event) => this.handleSubmit(event)} size="large">
            <Segment stacked>
              <Form.Input
                fluid
                name="firstName"
                icon="user"
                iconPosition="left"
                placeholder="First name"
                onChange={this.handleChange}
                className={this.handleInputError(this.state.errors, "firstName")}
                value={this.state.user.firstName}
                type="text"
              />

              <Form.Input
                fluid
                name="lastName"
                icon="user"
                iconPosition="left"
                placeholder="Last name"
                onChange={this.handleChange}
                className={this.handleInputError(this.state.errors, "lastName")}
                value={this.state.user.lastName}
                type="text"
              />

              <Form.Input
                fluid
                name="username"
                icon="user circle"
                iconPosition="left"
                placeholder="Username"
                onChange={this.handleChange}
                className={this.handleInputError(this.state.errors, "username")}
                value={this.state.user.username}
                type="text"
              />

            <Form.Input
                fluid
                name="eMail"
                icon="mail"
                iconPosition="left"
                placeholder="E-mail adress"
                onChange={this.handleChange}
                className={this.handleInputError(this.state.errors, "eMail")}
                value={this.state.user.eMail}
                type="email"
              />

              <Form.Input
                fluid
                name="password"
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(this.state.errors, "password")}
                value={this.state.user.password}
                type="password"
              />

              <Form.Input
                fluid
                name="repeatedPassword"
                icon="repeat"
                iconPosition="left"
                placeholder="Password Confirmation"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(this.state.errors, "repeatedPassword")}
                value={this.state.user.repeatedPassword}
                type="password"
              />

              <Button color="violet" fluid size="large">
                Submit
              </Button>
            </Segment>
          </Form>
          {this.state.errors.length > 0 && (
            <Message error>
              <h3>Error</h3>
              {this.displayErrors(this.state.errors)}
            </Message>
          )}
          <Message>
            Already a user? <Link to="/login">Login</Link>
          </Message>
        </Grid.Column>
      </Grid>
    );
  }
}

export default Register;