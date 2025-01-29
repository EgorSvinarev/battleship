import React, {useEffect} from "react";
import "../styles/componentsStyles/profile.scss";
import FriendsList from "./FriendsList";
import {Link} from "react-router-dom";
import {Avatar, Box, Button, Card, CardContent, Grid2, Tooltip, Typography, useMediaQuery,} from "@mui/material";
import UserData from "../types/UserData";
import {useAppDispatch} from "../store";
import {getUser, selectCurrentUser, selectIsUserLoading} from "../store/users";
import {getUserIdFromToken} from "../helpers";
import {useSelector} from "react-redux";

const players: UserData[] = [
  {id: 1, nickname: "Player1", rang: 1, win: 20, lost: 5, total: 25},
  {id: 2, nickname: "Player2", rang: 2, win: 15, lost: 10, total: 25},
  // Добавьте больше игроков по мере необходимости
];

export function Profile() {
  const isSmallScreen = useMediaQuery('(max-width:720px)');
  const currentUser = useSelector(selectCurrentUser)
  const isUserLoading = useSelector(selectIsUserLoading)
  const dispatch = useAppDispatch()

  useEffect(() => {
    dispatch(getUser(getUserIdFromToken()))
  }, [dispatch]);

  console.log(currentUser)

  return (
    <Card sx={{flexGrow: 1}}>
      <CardContent>
        <Box sx={{flexGrow: 2}} className="profile">
          <Grid2
            container
            spacing={1}
            justifyContent={"space-between"}
            wrap="wrap"
            sx={isSmallScreen ? {flexDirection: "column", alignItems: "center"} : {}}
          >
            <Grid2
              container
              spacing={1}
              justifyContent={"space-around"}
              alignContent={"space-between"}
            >
              <Grid2
                container
                sx={{flexDirection: "column", alignItems: "center"}}
              >
                <Tooltip title="Нажмите, чтобы перейти в профиль">
                  <Link to="/me">
                    <Avatar
                      src="/img/user-avatar.png"
                      alt="User Avatar"
                      sx={{width: "100px", height: "100px"}}
                    />
                  </Link>
                </Tooltip>
                <Link to="/me">
                  <Typography sx={{fontWeight: "bold", fontSize: "20px", filter: isUserLoading ? 'blur(5px)' : 'none', transition: 'filter 0.3s ease'}}>
                    {currentUser?.username ?? "Гость"}
                  </Typography>
                </Link>

                <b>Ранг: {currentUser?.xp ?? 0}</b>

              </Grid2>
            </Grid2>

            {!isSmallScreen && (
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
            )}
          </Grid2>
        </Box>
      </CardContent>
    </Card>
  );
};

export default Profile;
