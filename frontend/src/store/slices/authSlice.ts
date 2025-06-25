import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface AuthState {
  user: {
    username: string;
    isAuthenticated: boolean;
  };
  loading: boolean;
  error: string | null;
}

const initialState: AuthState = {
  user: {
    username: '',
    isAuthenticated: false,
  },
  loading: false,
  error: null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action: PayloadAction<string>) => {
      state.loading = false;
      state.user = {
        username: action.payload,
        isAuthenticated: true,
      };
      state.error = null;
    },
    loginFailure: (state, action: PayloadAction<string>) => {
      state.loading = false;
      state.error = action.payload;
    },
    logout: (state) => {
      state.user = {
        username: '',
        isAuthenticated: false,
      };
      state.error = null;
    },
    clearError: (state) => {
      state.error = null;
    },
  },
});

export const { loginStart, loginSuccess, loginFailure, logout, clearError } = authSlice.actions;
export default authSlice.reducer;