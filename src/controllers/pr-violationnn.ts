import { Request, Response } from 'express';
import UserModel from '../models/User'; // direct import (violation trigger)

export async function getUser(req: Request, res: Response) {
  const user = await UserModel.findById(req.params.id); // direct DB query
  if (!user) {
    return res.status(404).json({ message: 'User not found' });
  }
  res.json(user);
}

export async function createUser(req: Request, res: Response) {
  const newUser = await UserModel.create(req.body); // direct DB write
  res.status(201).json(newUser);
}
