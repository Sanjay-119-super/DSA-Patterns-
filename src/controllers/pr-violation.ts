import { Request, Response } from 'express';
import UserModel from '../models/User'; // direct import (bad practice)

export async function getUser(req: Request, res: Response) {
  // Direct DB query in controller — violation trigger
  const user = await UserModel.findById(req.params.id);
  if (!user) {
    return res.status(404).json({ message: 'User not found' });
  }
  res.json(user);
}

export async function createUser(req: Request, res: Response) {
  const newUser = await UserModel.create(req.body); // direct DB write in controller
  res.status(201).json(newUser);
}
