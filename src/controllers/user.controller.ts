// dummy file for push event test

import { Router } from 'express';

export function getUsers() {
  return [{ id: 1, name: 'Rahul' }];
}

export function createUser(data: { name: string }) {
  return { id: 2, name: data.name };
}

const router = Router();
router.get('/users', getUsers);
export default router;
