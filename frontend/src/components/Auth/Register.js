import React from "react";
import { Grid, Form, Segment, Button, Header, Message, Icon } from "semantic-ui-react";
import { Link } from "react-router-dom";
import Axios from "../../apis/Axios";

class Register extends React.Component {
  constructor(props) {
    super(props);

    let user = {
      firstName: "",
      lastName: "",
      username: "",
      eMail: "",
      gender: "",
      password: "",
      repeatedPassword: "",
    };
  
    this.state = {
      user: user,
      errors: [],
    };
  }

  displayErrors = errors =>
  errors.map((error, i) => <p key={i}>{error.message}</p>);

  handleChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let user = this.state.user;
    user[name] = value;

    this.setState({ user: user });
  };

  async handleSubmit(event) {
    event.preventDefault();
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
      alert("Could not register.")
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
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "firstName")}
                type="text"
              />

              <Form.Input
                fluid
                name="lastName"
                icon="user"
                iconPosition="left"
                placeholder="Last name"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "lastName")}
                type="text"
              />

              <Form.Input
                fluid
                name="username"
                icon="user circle"
                iconPosition="left"
                placeholder="Username"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "username")}
                type="text"
              />

            <Form.Input
                fluid
                name="eMail"
                icon="mail"
                iconPosition="left"
                placeholder="E-mail adress"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "eMail")}
                type="email"
              />

              <Form.Input
                fluid
                name="password"
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "password")}
                type="password"
              />

              <Form.Input
                fluid
                name="repeatedPassword"
                icon="repeat"
                iconPosition="left"
                placeholder="Password Confirmation"
                onChange={(event) => this.handleChange(event)}
                className={this.handleInputError(errors, "repeatedPassword")}
                type="password"
              />

              <Button color="violet" fluid size="large">
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
            Already a user? <Link to="/login">Login</Link>
          </Message>
        </Grid.Column>
      </Grid>
    );
  }
}

export default Register;