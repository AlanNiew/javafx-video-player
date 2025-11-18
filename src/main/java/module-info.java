module com.zszq.javafxstudy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media; // 添加媒体模块支持

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    // 添加缺失的模块依赖
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;

    opens com.zszq.javafxstudy to javafx.fxml;
    exports com.zszq.javafxstudy;
}