import React from "react";
import Profile from "../components/Profile";
import GameModeButtons from "../components/Buttons/GameModeButtons";
import StatsTable from "../components/StatsTable";
import "../styles/pageStyles/homePage.scss";
import {Box, Card, CardContent, Grid, Typography} from "@mui/material";
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";

const HomePage: React.FC = () => {
  return (
    <div className="page">
      <HeaderContainer/>
      <div className="content">
        <Box>
          <Grid container spacing={2} alignItems="center">
            {/* Обернем кнопку и профиль в отдельный контейнер с настройками размера */}
            <Grid item xs={12} sm={4} md={3} sx={{height: "100%"}}>
              <Card sx={{flexGrow: 1, 'min-height': '230px'}}>
                <CardContent sx={{height: "100%"}}>
                  <GameModeButtons/>
                </CardContent>
              </Card>
            </Grid>

            <Grid item xs={12} sm={8} md={9}>
              <Profile/>
            </Grid>

            {/* Карточка со статистикой */}
            <Grid item xs={12}>
              <Card sx={{flexGrow: 1, 'min-height': '250px'}}>
                <CardContent>
                  <Typography sx={{marginBottom: "2px"}}>СТАТИСТИКА</Typography>
                  <StatsTable/>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Box>
      </div>
    </div>
  );
};

export default HomePage;
