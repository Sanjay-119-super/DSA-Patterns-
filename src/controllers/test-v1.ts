import UserModel from '../models/User';
export async function getUser(req, res) {
  const user = await UserModel.findById(req.params.id);
  res.json(user);
}
