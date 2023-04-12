import React from 'react';
import axios from 'axios';
import { render, fireEvent, waitFor, screen } from '@testing-library/react';
import LoginSignup from './pages/Login-SignUp.jsx';

jest.mock('axios');

describe('LoginSignup', () => {
  test('renders login form', () => {
    render(<LoginSignup />);
    expect(screen.getByLabelText('Email')).toBeInTheDocument();
    expect(screen.getByLabelText('Password')).toBeInTheDocument();
    expect(screen.getByText('Don\'t have an account?')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Login' })).toBeInTheDocument();
  });
  test('renders signup form', () => {
      render(<LoginSignup />);
      fireEvent.click((screen.getByRole('button', { name: 'Sign Up' })));
      expect(screen.getByLabelText('Name')).toBeInTheDocument();
      expect(screen.getByLabelText('Email')).toBeInTheDocument();
      expect(screen.getByLabelText('Password')).toBeInTheDocument();
      expect(screen.getByLabelText('Confirm Password')).toBeInTheDocument();
      expect(screen.getByRole('button', { name: 'Sign Up' })).toBeInTheDocument();
  });
  test('signs in successfully', async () => {
      render(<LoginSignup />);
      await waitFor(() => {
        expect(screen.getByText('Sign Up')).toBeInTheDocument();
      });
      fireEvent.click((screen.getByRole('button', { name: 'Sign Up' })));
      axios.post.mockResolvedValueOnce({ data: { success: 'true' } });
      fireEvent.change(screen.getByLabelText('Name'), {
        target: { value: 'John' },
      });
      fireEvent.change(screen.getByLabelText('Email'), {
         target: { value: 'test@example.com' },
      });
      fireEvent.change(screen.getByLabelText('Password'), {
        target: { value: 'password123' },
      });
      fireEvent.change(screen.getByLabelText('Confirm Password'), {
        target: { value: 'password123' },
      });
      fireEvent.click(screen.getByRole('button', { name: 'Sign Up' }));
      await waitFor(() => {
        expect(screen.getByText('Sign Up Successful!')).toBeInTheDocument();
      });
  });

  test('shows error message when passwords do not match', async () => {
        render(<LoginSignup />);
        await waitFor(() => {
          expect(screen.getByText('Sign Up')).toBeInTheDocument();
        });
        fireEvent.click((screen.getByRole('button', { name: 'Sign Up' })));
        axios.post.mockResolvedValueOnce({ data: { success: 'false', passwordmatch: 'false' } });
        fireEvent.change(screen.getByLabelText('Name'), {
          target: { value: 'John' },
        });
        fireEvent.change(screen.getByLabelText('Email'), {
           target: { value: 'test@example.com' },
        });
        fireEvent.change(screen.getByLabelText('Password'), {
          target: { value: 'password123' },
        });
        fireEvent.change(screen.getByLabelText('Confirm Password'), {
          target: { value: 'password1234' },
        });
        fireEvent.click(screen.getByRole('button', { name: 'Sign Up' }));
        await waitFor(() => {
          expect(screen.getByText('Passwords do not match.')).toBeInTheDocument();
        });
    });

    test('shows error message when passwords is less than 8 characters', async () => {
        render(<LoginSignup />);
        await waitFor(() => {
          expect(screen.getByText('Sign Up')).toBeInTheDocument();
        });
        fireEvent.click((screen.getByRole('button', { name: 'Sign Up' })));
        axios.post.mockResolvedValueOnce({ data: { success: 'false', passwordLength: 'false' } });
        fireEvent.change(screen.getByLabelText('Name'), {
          target: { value: 'John' },
        });
        fireEvent.change(screen.getByLabelText('Email'), {
           target: { value: 'test@example.com' },
        });
        fireEvent.change(screen.getByLabelText('Password'), {
          target: { value: 'p' },
        });
        fireEvent.change(screen.getByLabelText('Confirm Password'), {
          target: { value: 'p' },
        });
        fireEvent.click(screen.getByRole('button', { name: 'Sign Up' }));
        await waitFor(() => {
          expect(screen.getByText('Password is less than 8 characters')).toBeInTheDocument();
        });
    });

    test('shows error message when the user already exists', async () => {
        render(<LoginSignup />);
        await waitFor(() => {
          expect(screen.getByText('Sign Up')).toBeInTheDocument();
        });
        fireEvent.click((screen.getByRole('button', { name: 'Sign Up' })));
        axios.post.mockResolvedValueOnce({ data: { success: 'false', userexists: 'false' } });
        fireEvent.change(screen.getByLabelText('Name'), {
          target: { value: 'John' },
        });
        fireEvent.change(screen.getByLabelText('Email'), {
           target: { value: 'test@example.com' },
        });
        fireEvent.change(screen.getByLabelText('Password'), {
          target: { value: 'p' },
        });
        fireEvent.change(screen.getByLabelText('Confirm Password'), {
          target: { value: 'p' },
        });
        fireEvent.click(screen.getByRole('button', { name: 'Sign Up' }));
        await waitFor(() => {
          expect(screen.getByText('User already exists')).toBeInTheDocument();
        });
    });

  test('shows error message when user does not exist', async () => {
   axios.post.mockResolvedValueOnce({ data: { success: 'false', loginuserexists: 'false' } });
   render(<LoginSignup />);
   fireEvent.change(screen.getByLabelText('Email'), {
     target: { value: 'test@example.com' },
   });
   fireEvent.change(screen.getByLabelText('Password'), {
     target: { value: 'password' },
   });
   fireEvent.click(screen.getByRole('button', { name: 'Login' }));
   await waitFor(() => {
     expect(screen.getByText('User does not exist. Please sign up first.')).toBeInTheDocument();
   });
  });

  test('logs in successfully', async () => {
   axios.post.mockResolvedValueOnce({ data: { success: 'true' } });
   render(<LoginSignup />);
   fireEvent.change(screen.getByLabelText('Email'), {
     target: { value: 'test@example.com' },
   });
   fireEvent.change(screen.getByLabelText('Password'), {
     target: { value: 'password' },
   });
   fireEvent.click(screen.getByRole('button', { name: 'Login' }));
   await waitFor(() => {
     expect(screen.getByText('Login successful, Welcome!')).toBeInTheDocument();
   });
  });

  test('shows error message when password is incorrect', async () => {
   axios.post.mockResolvedValueOnce({ data: { success: 'false', loginpasswordmatch: 'false' } });
   render(<LoginSignup />);
   fireEvent.change(screen.getByLabelText('Email'), {
     target: { value: 'test@example.com' },
   });
   fireEvent.change(screen.getByLabelText('Password'), {
     target: { value: 'password' },
   });
   fireEvent.click(screen.getByRole('button', { name: 'Login' }));
   await waitFor(() => {
     expect(screen.getByText('User exists but your password is incorrect')).toBeInTheDocument();
   });
  });
});