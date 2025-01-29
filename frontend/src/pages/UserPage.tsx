import React from "react";
import "../styles/pageStyles/userPage.scss";
import {Avatar, Box, Card, CardContent, Grid2, Typography,} from "@mui/material";
import FriendsList from "../components/FriendsList";
import UserData from "../types/UserData";
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";

const players: UserData[] = [
  {id: 1, nickname: "Player1", rang: 1, win: 20, lost: 5, total: 25},
  {id: 2, nickname: "Player2", rang: 2, win: 15, lost: 10, total: 25},
  // Добавьте больше игроков по мере необходимости
];

const UserPage: React.FC = () => {
  return (
    <div className="page">
      <HeaderContainer/>
      <div className="content">
        <Box>
          <Grid2 container spacing={2} alignItems="center">
            <Card sx={{width: "100%"}}>
              <CardContent>
                <Grid2 container alignItems="center" spacing={2}>
                  <Avatar
                    src="/img/user-avatar.png"
                    alt="User Avatar"
                    sx={{width: "100px", height: "100px"}}
                  />
                  <Box>
                    <Typography sx={{fontWeight: "bold", fontSize: "20px"}}>
                      @coolvovan2003
                    </Typography>
                    <Typography>
                      <b>Звание:</b> Крутой мужчина
                    </Typography>
                    <Typography>
                      <b>Достижения:</b> Самая мощная абоба на диком западе
                    </Typography>
                    <Typography>
                      <b>Место в рейтинге:</b> 30
                    </Typography>
                  </Box>
                </Grid2>
              </CardContent>
            </Card>
            <Card sx={{flexGrow: 1}}>
              <CardContent>
                <Grid2
                  container
                  spacing={0.5}
                  justifyContent={"center"}
                  flexDirection={"column"}
                  sx={{flexGrow: 1}}
                >
                  <Typography sx={{marginBotton: "2px"}}>
                    СПИСОК ДРУЗЕЙ
                  </Typography>
                  <FriendsList data={players}/>
                </Grid2>
              </CardContent>
            </Card>
            <Card sx={{flexGrow: 1}}>
              <CardContent style={{height: '190px'}}>СТАТИСТИКА</CardContent>
            </Card>
          </Grid2>
        </Box>
      </div>
    </div>
  );
  };

      export default UserPage;
