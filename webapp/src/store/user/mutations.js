export function auth(state, payload) {
  state.user.company = payload.company;
  state.user.role = payload.role;
}
export function logout(state) {
  state.user.company = "";
  state.user.role = "";
}
