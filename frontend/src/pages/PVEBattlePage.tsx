import React from 'react';
//import Footer from '../components/Footer';
import '../styles/pageStyles/pveBattlePage.scss';
import DragAndDropContainer from '../components/DragAndDropComtainer';
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";

const PVEBattlePage: React.FC = () => {
  return (
    <div className="pvpBattlePage">
      <HeaderContainer/>
      <main>
        WELCOME TO PVE BATTLE PAGE
        <h1>React Drag-and-Drop с TypeScript EXAMPLE</h1>
        <DragAndDropContainer/>
      </main>
      {/* <Footer /> */}
    </div>
  );
};

export default PVEBattlePage;