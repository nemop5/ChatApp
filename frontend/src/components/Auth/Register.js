import React from "react";
import { Grid, Form, Segment, Button, Header, Message, Icon } from "semantic-ui-react";
import { Link } from "react-router-dom";
import Axios from "../../apis/Axios";

class Register extends React.Component {
  state = {
    firstName: "",
    lastName: "",
    username: "",
    eMail: "",
    gender: "",
    password: "",
    repeatedPassword: "",
  };

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  async handleSubmit(event) {
    event.preventDefault();

    let userDto = {
      firstName: "",
      lastName: "",
      username: "",
      eMail: "",
      gender: "",
      password: "",
      repeatedPassword: "",
    };

    try{
      await Axios.post("/users", userDto);
    }catch(error){
      console.log(error);
      alert("Could not register.")
    }
  };

  render() {
    const { fitstName, lastName, username, eMail, password, repeatedPassword } = this.state;

    return (
      <Grid textAlign="center" verticalAlign="middle" className="app">
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as="h2" icon color="orange" textAlign="center">
            <Icon name="user plus" color="orange" />
            Register for ChatApp
          </Header>
          <Form onSubmit={this.handleSubmit} size="large">
            <Segment stacked>
              <Form.Input
                fluid
                name="fistName"
                icon="user"
                iconPosition="left"
                placeholder="First name"
                onChange={this.handleChange}
                value={fitstName}
                type="text"
              />

              <Form.Input
                fluid
                name="lastName"
                icon="user"
                iconPosition="left"
                placeholder="Last name"
                onChange={this.handleChange}
                value={lastName}
                type="text"
              />

              <Form.Input
                fluid
                name="username"
                icon="user circle"
                iconPosition="left"
                placeholder="Username"
                onChange={this.handleChange}
                value={username}
                type="text"
              />

            <Form.Input
                fluid
                name="eMail"
                icon="mail"
                iconPosition="left"
                placeholder="E-mail adress"
                onChange={this.handleChange}
                value={eMail}
                type="email"
              />

              <Form.Input
                fluid
                name="password"
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                onChange={this.handleChange}
                value={password}
                type="password"
              />

              <Form.Input
                fluid
                name="repeatedPassword"
                icon="repeat"
                iconPosition="left"
                placeholder="Password Confirmation"
                onChange={this.handleChange}
                value={repeatedPassword}
                type="password"
              />

              <Button color="orange" fluid size="large">
                Submit
              </Button>
            </Segment>
          </Form>
          <Message>
            Already a user? <Link to="/login">Login</Link>
          </Message>
        </Grid.Column>
      </Grid>
    );
  }
}

export default Register;