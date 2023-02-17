<#--noinspection InjectedReferences-->
<#import '/spring.ftl' as spring>
<#import 'macros/forms/_forms.ftl' as forms>
<#import 'macros/image.ftl' as image>
<#import 'macros/table.ftl' as table>

<#function staticUrl url>
  <#return "/assets/${url}" >
</#function>


<#macro baseTemplate
  pageTitle=""
>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${staticUrl("style.css")}">
    <link rel="icon" type="image/x-icon" href="${staticUrl("favicon.svg")}">
    <link rel="apple-touch-icon" sizes="180x180" href="${staticUrl("apple-touch-icon.png")}">
    <link rel="icon" type="image/png" sizes="32x32" href="${staticUrl("favicon-32x32.png")}">
    <link rel="icon" type="image/png" sizes="16x16" href="${staticUrl("favicon-16x16.png")}">
    <link rel="manifest" href="${staticUrl("site.webmanifest")}">
    <link rel="mask-icon" href="${staticUrl("safari-pinned-tab.svg")}" color="#5bbad5">
    <meta name="msapplication-TileColor" content="#603cba">
    <meta name="theme-color" content="#ffffff">


  </head>
    <body>
      <main>
        <#nested>
      </main>
    </body>
  </html>
</#macro>
