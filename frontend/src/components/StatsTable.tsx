import React, {useEffect} from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from "@mui/material";
import "../styles/componentsStyles/statsTable.scss";
import UserData from "../types/UserData";
import {useAppDispatch} from "../store";
import {getUserIdFromToken} from "../helpers";
import {getUsers, selectUsers} from "../store/users";
import {useSelector} from "react-redux";

interface TableProps {
  data: UserData[];
}

export default function StatsTable() {
  const dispatch = useAppDispatch()

  useEffect(() => {
    dispatch(getUsers(
      {
        paging: {
          offset: 1,
          limit: 10
        }
      }
    ))
  }, [dispatch]);

  const users = useSelector(selectUsers)
  console.log(users)

  return (
    <TableContainer component={Paper} sx={{ maxHeight:150, overflowY: 'scroll' }}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>NICKNAME</TableCell>
            <TableCell>RANG</TableCell>
            <TableCell>WIN</TableCell>
            <TableCell>LOST</TableCell>
            <TableCell>TOTAL</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {users.map((user) => (
            <TableRow key={user.id}>
              <TableCell>{user.username}</TableCell>
              <TableCell>{user.xp}</TableCell>
              <TableCell>{user.statistics.win}</TableCell>
              <TableCell>{user.statistics.lost}</TableCell>
              <TableCell>{user.statistics.total}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};