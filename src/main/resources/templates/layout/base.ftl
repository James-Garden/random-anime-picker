<#--noinspection InjectedReferences-->
<#import '/spring.ftl' as spring>
<#import 'macros/forms/_forms.ftl' as forms>

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
    <link rel="icon" type="image/x-icon" href="${staticUrl("favicon.ico")}">
  </head>
    <body>
      <main>
        <#nested>
      </main>
    </body>
  </html>
</#macro>
